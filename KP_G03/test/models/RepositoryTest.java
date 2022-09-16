package models;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**This class contains method for testing Repository class
 * @author Pal Patel
 *
 */
public class RepositoryTest{
	

//	@Test
//	public void tIssueList(){
//		Repository testIssueList= new Repository();
//		testIssueList.setIssueList("IssueList");
//		assertEquals(testIssueList.getIssueList(),"IssueList");
//	}
	
	@Test
	public void tContributorURL(){
		Repository testContributorURL= new Repository();
		testContributorURL.setContributorURL("ContributorURL");
		assertEquals(testContributorURL.getContributorURL(),"ContributorURL");
	}
	
//	@Test
//	public void tRepoCollabs(){
//		Repository testRepoCollabs= new Repository();
//		testRepoCollabs.setRepoCollabs("RepoCollabs");
//		assertEquals(testRepoCollabs.getRepoCollabs(),"RepoCollabs");
//	}
	
	@Test
	public void tLanguage(){
		Repository testLanguage= new Repository();
		testLanguage.setLanguage("Python");
		assertEquals(testLanguage.getLanguage(),"Python");
	}
	
	@Test
	public void tStars(){
		Repository testStars= new Repository();
		testStars.setStars(5);
		assertEquals(testStars.getStars(), 5);
	}
	
	@Test
	public void tCreatedAt(){
		Repository testCreatedAt= new Repository();
		testCreatedAt.setCreatedAt("January 1,2021");
		assertEquals(testCreatedAt.getCreatedAt(),"January 1,2021");
	}
	

	@Test
	public void tForks(){
		Repository testForks= new Repository();
		testForks.setForks(100);
		assertEquals(testForks.getForks(),100);
	}
	

	@Test
	public void tWatchers_count(){
		Repository testWatchers_count= new Repository();
		testWatchers_count.setWatchers_count(2000);
		assertEquals(testWatchers_count.getWatchers_count(),2000);
	}
	

	@Test
	public void tScore(){
		Repository testScore= new Repository();
		testScore.setScore(10);
		assertEquals(testScore.getScore(),10);
	}
	

	@Test
	public void tVisibility(){
		Repository testVisibility= new Repository();
		testVisibility.setVisibility("Public");
		assertEquals(testVisibility.getVisibility(),"Public");
	}
	
	@Test
	public void testid(){
		Repository testids= new Repository();
		testids.setId("IssueList");
		assertEquals(testids.getId(),"IssueList");
	}
	

	@Test
	public void tLogin(){
		Repository testLogin= new Repository();
		testLogin.setLogin("Git User");
		assertEquals(testLogin.getLogin(),"Git User");
	}
	

	@Test
	public void tRepoName(){
		Repository testRepoName= new Repository();
		testRepoName.setRepoName("Hello World");
		assertEquals(testRepoName.getRepoName(),"Hello World");
	}
	

	@Test
	public void tRepourl(){
		Repository testRepourl= new Repository();
		testRepourl.setRepourl("www.github.com");
		assertEquals(testRepourl.getRepourl(),"www.github.com");
	}
	

	@Test
	public void tCommitsUrl(){
		Repository testCommitsUrl= new Repository();
		testCommitsUrl.setCommitsUrl("www.commitsurl.com");
		assertEquals(testCommitsUrl.getCommitsUrl(),"www.commitsurl.com");
	}
	
	@Test
	public void tIssuesUrl(){
		Repository testIssuesUrl= new Repository();
		testIssuesUrl.setIssuesUrl("www.issuesurl.com");
		assertEquals(testIssuesUrl.getIssuesUrl(),"www.issuesurl.com");
	}
	
//	@Test
//	public void tTopics(){
//		Repository testTopics= new Repository();
//		testTopics.setTopics("Algorithms", "Java");
//		assertEquals(testTopics.getTopics(),"Algorithms", "Java");
//	}
	
}