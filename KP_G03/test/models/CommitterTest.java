package models;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**This class contains methods for testing CommitterTest class
 * @author Pal Patel
 *
 */
public class CommitterTest{
	
	@Test
	public void tName(){
		Committer testName= new Committer();
		testName.setName("Name here");
		assertEquals(testName.getName(),"Name here");
	}
	
	@Test
	public void tCommitNum(){
		Committer testCommitNum= new Committer();
		testCommitNum.setCommitNum(77);
		assertEquals((int)testCommitNum.getCommitNum(),77);
	}
}