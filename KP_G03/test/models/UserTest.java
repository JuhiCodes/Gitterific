package models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**This class contains methods for testing User class
 * @author Pal Patel
 *
 */
public class UserTest {

	
	@Test
	public void tUserRepos(){
		
	}
	
	
	@Test
	public void tFollowers(){
		User testFollowers= new User();
		testFollowers.setFollowers(5000);
		assertEquals((int)testFollowers.getFollowers(),5000);
	}
	
	@Test
	public void tName(){
		User testName= new User();
		testName.setName("Name here");
		assertEquals(testName.getName(),"Name here");
	}
	
	@Test
	public void tPublicRepos(){
		User testPublicRepos= new User();
		testPublicRepos.setPublicRepos(5433);
		assertEquals((int)testPublicRepos.getPublicRepos(),5433);
	}
	
	@Test
	public void tRepoURL(){
		User testRepoURL= new User();
		testRepoURL.setRepoURL("repo.url");
		assertEquals(testRepoURL.getRepoURL(),"repo.url");
	}
	
	@Test
	public void tFollowingURL(){
		User testFollowingURL= new User();
		testFollowingURL.setFollowingURL("following.url");
		assertEquals(testFollowingURL.getFollowingURL(),"following.url");
	}
	
	@Test
	public void tFollowersURL(){
		User testFollowersURL= new User();
		testFollowersURL.setFollowersURL("followers.url");
		assertEquals(testFollowersURL.getFollowersURL(),"followers.url");
	}
	
	@Test
	public void tHtmlURL(){
		User testHtmlURL= new User();
		testHtmlURL.setHtmlURL("html.url");
		assertEquals(testHtmlURL.getHtmlURL(),"html.url");
	}
	@Test
	public void getLogin(){
		User testLogin= new User();
		testLogin.setLogin("login here");
		assertEquals(testLogin.getLogin(),"login here");
	}
	
	@Test
	public void getAvatarURL(){
		User setAvatarURL= new User();
		setAvatarURL.setAvatarURL("avatar.url");
		assertEquals(setAvatarURL.getAvatarURL(),"avatar.url");
	}
	
	
	@Test
	public void tFollowing(){
		User testFollowing= new User();
		testFollowing.setFollowing(6565);
		assertEquals((int)testFollowing.getFollowing(),6565);
	}
	
	
}
