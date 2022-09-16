package models;

import java.util.ArrayList;
//import java.util.List;
//import models.UserRepos;


import controllers.UserDetails;
/**
 * Model class for storing user data
 * 
 * @author Krupali bhatt
 * @version 1.0
 */

public class User {
	public String repoURL;
	public Integer following;
	public Integer followers;
	public String Name;
	public Integer publicRepos;
	public String followingURL;
	public String followersURL;
	public String htmlURL;
	public String login;
	public String AvatarURL;
	public ArrayList<Repository> userReposlist;


	public ArrayList<Repository> getUserReposlist() {
		return userReposlist;
	}
	public void setUserReposlist(String repoURL) {
		userReposlist= UserDetails.listUserRepos(repoURL);
	}
	public Integer getFollowers() {
		return followers;
	}
	public void setFollowers(Integer followers) {
		this.followers = followers;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getPublicRepos() {
		return publicRepos;
	}
	public void setPublicRepos(Integer publicRepos) {
		this.publicRepos = publicRepos;
	}
	public String getRepoURL() {
		return repoURL;
	}
	public void setRepoURL(String repoURL) {
		this.repoURL = repoURL;
	}
	public String getFollowingURL() {
		return followingURL;
	}
	public void setFollowingURL(String followingURL) {
		this.followingURL = followingURL;
	}
	public String getFollowersURL() {
		return followersURL;
	}
	public void setFollowersURL(String followersURL) {
		this.followersURL = followersURL;
	}
	public String getHtmlURL() {
		return htmlURL;
	}
	public void setHtmlURL(String htmlURL) {
		this.htmlURL = htmlURL;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getAvatarURL() {
		return AvatarURL;
	}
	public void setAvatarURL(String avatarURL) {
		AvatarURL = avatarURL;
	}
	public Integer getFollowing() {
		return following;
	}
	public void setFollowing(Integer following) {
		this.following = following;
	}



}