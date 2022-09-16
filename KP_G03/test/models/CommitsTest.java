package models;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import models.Commits;

/**This class contains methods for testing Commits class
 * @author Pal Patel
 *
 */
public class CommitsTest{ 
	@Test
	public void tCommitterName(){
		Commits testCommitterName= new Commits();
		testCommitterName.setCommitterName("Committer Name");
		assertEquals(testCommitterName.getCommitterName(),"Committer Name");
	}
	
	@Test
	public void tCommitterId(){
		Commits testCommitterId= new Commits();
		testCommitterId.setCommitterId(7085);
		assertEquals((int)testCommitterId.getCommitterId(),7085);
	}
	
	@Test
	public void tTotalCommits(){
		Commits testTotalCommits= new Commits();
		testTotalCommits.setTotalCommits(242);
	    assertEquals((int)testTotalCommits.getTotalCommits(),242);
	}	
	
	@Test
	public void tTotal(){
		Commits testTotal= new Commits();
		testTotal.setTotal(6565);
		assertEquals(testTotal.getTotal(),6565);
	}
	
	@Test
	public void getAddition(){
		Commits testAddition= new Commits();
		testAddition.setAddition(3434);
		assertEquals((int)testAddition.getAddition(),3434);
	}
		
	@Test
	public void tDeletion(){
		Commits testDeletion= new Commits();
		testDeletion.setDeletion(7567);
		assertEquals((int)testDeletion.getDeletion(),7567);
	}
		
	@Test
	public void tCommitName(){
		Commits testCommitterName= new Commits();
		testCommitterName.setCommitName("Committer Name");
		assertEquals(testCommitterName.getCommitName(),"Committer Name");
	}	
	
}