package models;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import controllers.UserDetails;
import controllers.RepoDetails;
import controllers.RepoIssues;
import models.Issues;
/**
 * Model class for storing Repository data
 * 
 * @author Jay Patel
 * @version 1.0
 */
public class Repository {

	//	public String authorProfile;
	public String id;
	public String repourl;
	public String createdAt;
	//	public String updatedAt;
	public String gitCommitsurl;
	public String commitsUrl;
	public String issuesUrl;
	public String repoName;
	public String login;
	public ArrayList<String> topics;
	public int forks;
	public int watchers_count;
	public int score;
	public int stars;
	public String language;
	public String visibility;
	public String contributorURL;
	public List<Issues> issueList = new ArrayList<Issues>();
	public ArrayList<String> RepoCollabs;




	public List<Issues> getIssueList() {
		return issueList;
	}

	public void setIssueList(String s) {
		issueList = RepoIssues.getIssueList(s);
	}

	public String getContributorURL() {
		return contributorURL;
	}

	public void setContributorURL(String contributorURL) {
		this.contributorURL = contributorURL;
	}

	public ArrayList<String> getRepoCollabs() {
		return RepoCollabs;
	}
	public void setRepoCollabs(String contributorURL) {
		RepoCollabs=RepoDetails.listCollabRepos(contributorURL);
	}



	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public int getForks() {
		return forks;
	}
	public void setForks(int forks) {
		this.forks = forks;
	}
	public int getWatchers_count() {
		return watchers_count;
	}
	public void setWatchers_count(int watchers_count) {
		this.watchers_count = watchers_count;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}





	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getRepoName() {
		return repoName;
	}
	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}
	//	public String getAuthorProfile() {
	//		return authorProfile;
	//	}
	//	public void setAuthorProfile(String authorProfile) {
	//		this.authorProfile = authorProfile;
	//	}
	public String getRepourl() {
		return repourl;
	}
	public void setRepourl(String repourl) {
		this.repourl = repourl;
	}
	//	public String getCreatedAt() {
	//		return createdAt;
	//	}
	//	public void setCreatedAt(String createdAt) {
	//		
	//		this.createdAt = createdAt;
	//	}
	//	public String getUpdatedAt() {
	//		return updatedAt;
	//	}
	//	public void setUpdatedAt(String updatedAt) {
	//		this.updatedAt = updatedAt;
	//	}
	public String getGitCommitsurl() {
		return gitCommitsurl;
	}
	public void setGitCommitsurl(String gitCommitsurl) {
		this.gitCommitsurl = gitCommitsurl;
	}
	public String getCommitsUrl() {
		return commitsUrl;
	}
	public void setCommitsUrl(String commitsUrl) {
		this.commitsUrl = commitsUrl;
	}
	public String getIssuesUrl() {
		return issuesUrl;
	}
	public void setIssuesUrl(String issuesUrl) {
		this.issuesUrl = issuesUrl;
	}

	public ArrayList<String> getTopics(){
		return topics;
	}

	public void setTopics(ArrayList<String> topics) {
		this.topics = topics;
	}

}