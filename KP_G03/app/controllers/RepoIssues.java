package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import models.Issues;
import models.Repository;
/**
 * This class is for working with Repository Issue data
 * 
 * @author Parth Parekh
 */
public class RepoIssues {
	static public List<Issues> IssueList = new ArrayList<Issues>();
	/**
	 * This method sets repo Issue details
	 * 
	 * @param Issue
	 */
	public static void setRepoIssueObject (JSONObject Issue) {
		Issues issueObj = new Issues();
		issueObj.setTitle(Issue.getString("title"));
		issueObj.setCreated_at(Issue.getString("created_at"));
		issueObj.setUpdated_at(Issue.getString("updated_at"));
		issueObj.setNum(Issue.getInt("number"));
		issueObj.setId(Issue.getInt("id"));
		issueObj.setState(Issue.getString("state"));
		IssueList.add(issueObj);
	}

	/**
	 * This method gets the Issue List
	 * 
	 * @param iss
	 * @return IssueList
	 */

	public static List<Issues> getIssueList(String iss){
		System.out.println("IN GET ISSUES ");
		String issueURL = iss;
		String trimmedIssueURL = "";
		int index = issueURL.indexOf("{");
		if(index != -1) {
			trimmedIssueURL = issueURL.substring(0, index);
		}
		System.out.println(trimmedIssueURL);
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("per_page", "20");
		// Calling the API
		ApiCall.getApiCall(trimmedIssueURL, map).thenAccept(reponseBody -> {
			JSONArray IssueArray = new JSONArray(reponseBody);

			System.out.println(reponseBody);
			ArrayList<JSONObject> listData = new ArrayList<JSONObject>();
			for(int i=0; i<IssueArray.length();i++) {
				listData.add(IssueArray.optJSONObject(i));
			}

			listData.parallelStream().forEach(RepoIssues::setRepoIssueObject);
		});
		return IssueList;
	}
}
