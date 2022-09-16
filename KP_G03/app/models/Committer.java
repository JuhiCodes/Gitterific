package models;
/**
 * Model class for storing committer data
 * 
 * @author Juhi Patel
 * @version 1.0
 */
public class Committer {
	public String name;
	public Integer commitNum;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCommitNum() {
		return commitNum;
	}
	public void setCommitNum(Integer commitNum) {
		this.commitNum = commitNum;
	}
}
