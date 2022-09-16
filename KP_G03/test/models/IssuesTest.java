package models;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**This class contains methods for testing Issues class
 * @author Pal Patel
 *
 */
public class IssuesTest{
	
	@Test
	public void tCreated_at(){
		Issues testCreated_at= new Issues();
		testCreated_at.setCreated_at("January 6,2021");
		assertEquals(testCreated_at.getCreated_at(),"January 6,2021");
	}
	
	@Test
	public void tUpdated_at(){
		Issues testUpdated_at= new Issues();
		testUpdated_at.setUpdated_at("February 2,2021");
		assertEquals(testUpdated_at.getUpdated_at(),"February 2,2021");
	}

	
	@Test
	public void tNum(){
		Issues testNum= new Issues();
		testNum.setNum(123);
		assertEquals(testNum.getNum(),123);
	}

	
	@Test
	public void tId(){
		Issues testId= new Issues();
		testId.setId(4019);
		assertEquals(testId.getId(),4019);
	}

	@Test
	public void tState(){
		Issues testState= new Issues();
		testState.setState("Running");
		assertEquals(testState.getState(),"Running");
	}
	
	@Test
	public void tTitle(){
		Issues testTitle= new Issues();
		testTitle.setTitle("Title");
		assertEquals(testTitle.getTitle(),"Title");
	}
	
	




}