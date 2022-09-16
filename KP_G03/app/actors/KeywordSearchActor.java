package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import controllers.ApiCall;
import controllers.RepoDetails;
import models.Repository;
import utils.Util;
import play.libs.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONArray;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import actors.TimeActor.getNewData;

/**
 * This actor class manages the search keyword functionality
 * @author KPG03
 */
public class KeywordSearchActor extends AbstractActor{

	private final ActorRef webSocket;
	private LinkedHashMap<String, List<Repository>> searchHistory = new LinkedHashMap<>();
	
	public KeywordSearchActor(ActorRef ws) {
		this.webSocket = ws;
	}
	
    @Override
    public void preStart() {
    	
       	context().actorSelection("/user/timeActor/")
                 .tell(new TimeActor.RegisterMsg(), self());
    }
    
    @Override
    public void postStop() {
    	
       	context().actorSelection("/user/timeActor/")
                 .tell(new TimeActor.DeRegisterMsg(), self());
    }
    
    public static Props props(ActorRef webSocket) {
        return Props.create(KeywordSearchActor.class, webSocket);
    }
    
	@Override
	public Receive createReceive() {
		return receiveBuilder()
   			 .match(ObjectNode.class, searchObject -> sendNewData(searchObject.get("keyword").textValue()))
   			 .match(getNewData.class, n -> sendUpdatedData())
			 .build();
	}
	
	private void sendNewData(String keyword) {
		if(searchHistory.containsKey(keyword.toLowerCase())) {
			System.out.println("in the search history");
			JsonNode jsonObject = Json.toJson(searchHistory);
			webSocket.tell(Util.createResponse(jsonObject, true), self());
		}
		else {
			System.out.println("Here it is ...");
			System.out.println(searchHistory);
			List<Repository> r = RepoDetails.getRepoDetails(keyword);
			List<Repository> res = getRepoList(keyword);
			System.out.println(res);
			System.out.println(searchHistory);
			searchHistory.put(keyword.toLowerCase(), res);
			System.out.println(searchHistory);
			JsonNode obj = Json.toJson(searchHistory);
			System.out.println(obj);
			webSocket.tell(Util.createResponse(obj, true), self());
		}
	}
	
	
	public static List<Repository> getRepoList (String word) {
		
		List<Repository> repos = new ArrayList<Repository>();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("accept", "application/vnd.github.v3+json");
		map.put("per_page", "10");
		map.put("q", word);

		String url = "https://api.github.com/search/repositories";

		ApiCall.getApiCall(url, map).thenAccept(responseBody -> {
			JSONObject json = new JSONObject(responseBody);
			//			CompletionStage<Done> result = cache.set("item.key", json.toString());
			repos.clear();
			org.json.JSONArray array = json.getJSONArray("items");

			ArrayList<JSONObject> listData = new ArrayList<JSONObject>();
			for(int i=0; i<array.length();i++) {
				listData.add(array.optJSONObject(i));
			}

			for(JSONObject repository: listData) {
				Repository obj = new Repository();
				
				obj.setVisibility(repository.getString("visibility"));
				obj.setForks(repository.getInt("forks"));
				obj.setIssuesUrl(repository.getString("issues_url"));
				obj.setWatchers_count(repository.getInt("watchers_count"));
				obj.setScore(repository.getInt("score"));
				obj.setStars(repository.getInt("stargazers_count"));
				obj.setCreatedAt(repository.getString("created_at").substring(0,10));
				obj.setContributorURL(repository.getString("contributors_url"));
				//		obj.setRepoCollabs(repository.getString("contributors_url"));
				//		obj.setIssueList(repository.getString("issue_url"));

				JSONObject owner = (JSONObject) repository.get("owner");
				obj.setLogin(owner.getString("login"));
				obj.setRepourl(owner.getString("repos_url"));
				obj.setRepoName(repository.getString("name"));

				Number id= repository.getNumber("id");
				String idtemp=id.toString();
				System.out.println("ID String"+idtemp);
				obj.setId(idtemp);
				obj.setGitCommitsurl(repository.getString("git_commits_url"));
				obj.setCommitsUrl(repository.getString("commits_url"));
				obj.setIssuesUrl(repository.getString("issues_url"));
				
				JSONArray arr = repository.getJSONArray("topics");
				ArrayList<String> topics = new ArrayList<String>();
				for(int i = 0;i< arr.length();i++) {
					topics.add(arr.getString(i));
				}

				obj.setTopics(topics);
				repos.add(obj);
			};
		});
		
		return repos;
	}
	
	private void sendUpdatedData() {
		searchHistory.keySet().parallelStream().forEach(keyword -> {
			List<Repository> r = getRepoList(keyword);
			searchHistory.put(keyword, r);
		});
		JsonNode obj = Json.toJson(searchHistory);
		webSocket.tell(Util.createResponse(obj, true), self());
	}
}
