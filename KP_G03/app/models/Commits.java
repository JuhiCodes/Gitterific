package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Model class for storing commits data
 * 
 * @author Juhi Patel
 * @version 1.0
 */
public class Commits {


	public static Integer totalCommits;
	public String commitName;
	public String commitUrl;
	public int total;
	public Integer addition;
	public Integer deletion;
	public String committerName;
	public Integer committerId;
	public static HashMap<String,Integer> countMap = new HashMap<String, Integer>();


	public String getCommitterName() {
		return committerName;
	}

	public void setCommitterName(String committerName) {
		this.committerName = committerName;
	}


	public Integer getCommitterId() {
		return committerId;
	}


	public void setCommitterId(Integer committerId) {
		this.committerId = committerId;
	}


	public static Integer getTotalCommits() {
		return totalCommits;
	}


	public static void setTotalCommits(Integer totalCommits) {
		Commits.totalCommits = totalCommits;
	}


	public int getTotal() {
		return total;
	}

	public void setTotal(int i) {
		this.total = i;
	}


	public Integer getAddition() {
		return addition;
	}


	public void setAddition(Integer addition) {
		this.addition = addition;
	}


	public Integer getDeletion() {
		return deletion;
	}


	public void setDeletion(Integer deletion) {
		this.deletion = deletion;
	}

	public String getCommitName() {
		return commitName;
	}


	public void setCommitName(String commitName) {
		this.commitName = commitName;
	}


	public String getCommitUrl() {
		return commitUrl;
	}


	public void setCommitUrl(String commitUrl) {
		this.commitUrl = commitUrl;
	}


	public void updateMap() {
		if(!countMap.containsKey(committerName)) {
			countMap.put(committerName, 1);
		}
		else {
			countMap.put(committerName,  countMap.get(committerName)+1);
		}
		System.out.println("Updated hashmap");
	}



}
