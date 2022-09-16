package controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import models.Commits;
import models.Committer;
import models.Repository;
/**
 * This class for stores commit details
 * 
 * @author Juhi Patel
 * @version 1.0
 */
public class CommitDetails {
	public static List<Commits> com = new ArrayList<Commits>();
	public static List<Committer> committers = new ArrayList<Committer>();
	public static HashMap<String, Integer> sorted;
	public static List<Integer> additionResult = new ArrayList<Integer>();
	public static List<Integer> deletionResult = new ArrayList<Integer>();
	public static Integer maxAdd;
	public static Integer maxDel;
	public static Integer minAdd;
	public static Integer minDel;
	public static OptionalDouble avgAdd;
	public static OptionalDouble avgDel;

	/**
	 * This method fetches commit details
	 * 
	 * @param r Repository
	 */
	public static String findcommit(Repository r) {

		JSONArray jsonObject = null;

		try {
			URIBuilder builder = new URIBuilder("https://api.github.com/repos/"+r.login+"/"+r.repoName+"/commits");
			builder.addParameter("accept", "application/vnd.github.v3+json");
			builder.addParameter("X-RateLimit-Reset", "1350085394");
			builder.addParameter("per_page", "10");

			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpResponse resp = null;

			HttpGet getAPI = new HttpGet(builder.build());
			resp = httpclient.execute(getAPI);

			StatusLine statusLine = resp.getStatusLine();
			System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
			String responseBody = EntityUtils.toString(resp.getEntity(), StandardCharsets.UTF_8);
			System.out.println(responseBody.length());

			try {
				jsonObject = new JSONArray(responseBody);
			}catch (JSONException err){
				err.printStackTrace();
			}

		} catch (URISyntaxException | IOException | RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		com.clear();
		committers.clear();

		for(int i=0; i<jsonObject.length(); i++) {
			Commits obj = new Commits();

			if(!JSONObject.NULL.equals(jsonObject.getJSONObject(i))) {
				String commitUrl= jsonObject.getJSONObject(i).getString("url");
				obj.setCommitUrl(commitUrl);
				JSONObject temptCommitter = (JSONObject)jsonObject.getJSONObject(i).get("author");
				String committerName= (String)temptCommitter.getString("login");
				obj.setCommitterName(committerName);
				Integer committerId= (Integer)temptCommitter.getNumber("id");
				obj.setCommitterId(committerId);
				JSONObject tempt = (JSONObject)jsonObject.getJSONObject(i).get("commit");
				String commitName= (String)tempt.getString("message");
				obj.setCommitName(commitName);	
				obj.updateMap();
				com.add(obj);


			}
		}

		HashMap<String, Integer> temp =Commits.countMap.entrySet()
				.stream()
				.sorted((i1, i2)
						-> i1.getValue().compareTo(
								i2.getValue()))
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(e1, e2) -> e1, LinkedHashMap::new));


		sorted = temp.entrySet() .stream() .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) .collect( Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		for (Commits c : com) {
			System.out.println(c.getCommitName());
		}

		return "ok";

	}

	/**
	 * This method computes commit statistics
	 * 
	 */
	public static void commitStatistics() {
		additionResult.clear();
		deletionResult.clear();

		int count =0;
		CommitDetails.committers.clear();
		Iterator hmIterator = CommitDetails.sorted.entrySet().iterator();
		while (hmIterator.hasNext()&& count<10) {
			Map.Entry mapElement = (Map.Entry)hmIterator.next();
			Committer c = new Committer();
			String name = (String) mapElement.getKey(); 
			c.setName(name);
			Integer commitNum = (Integer) mapElement.getValue();
			c.setCommitNum(commitNum);
			CommitDetails.committers.add(c);
			count++;
		}

		for(Commits c:CommitDetails.com) {
			JSONObject results = addDelStats(c.commitUrl);
			JSONObject temptStats = (JSONObject)results.get("stats");
			if(!JSONObject.NULL.equals(temptStats)) {
				System.out.println("In stats object");
				Integer addition = (Integer)temptStats.getInt("additions");
				additionResult.add(addition);
				Integer deletion = (Integer)temptStats.getInt("deletions");
				deletionResult.add(deletion);
			}
			avgAdd = additionResult
					.stream()
					.mapToDouble(a -> a)
					.average();


			avgDel = deletionResult
					.stream()
					.mapToDouble(a -> a)
					.average();

			maxAdd = additionResult.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
			maxDel = deletionResult.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();

			minAdd = additionResult.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMin();
			minDel = deletionResult.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMin();	

		}


	}

	/**
	 * This method computes addition deletion statistics
	 * 
	 * @param url 
	 * @return jsonObject
	 */    
	public static JSONObject addDelStats(String url) {

		JSONObject jsonObject = null;
		try {
			URIBuilder builder = new URIBuilder(url);
			builder.addParameter("accept", "application/vnd.github.v3+json");
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpResponse resp = null;


			HttpGet getAPI = new HttpGet(builder.build());
			resp = httpclient.execute(getAPI);

			StatusLine statusLine = resp.getStatusLine();
			System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
			String responseBody = EntityUtils.toString(resp.getEntity(), StandardCharsets.UTF_8);
			System.out.println(responseBody.length());

			try {
				jsonObject = new JSONObject(responseBody);
			}catch (JSONException err){
				err.printStackTrace();
			}

		} catch (URISyntaxException | IOException | RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
}