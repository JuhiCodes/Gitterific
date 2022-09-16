package models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**This class contains methods for testing RepoData class
 * @author Pal Patel
 *
 */
public class RepoDataTest {

	
	@Test
	public void testrepo(){
		RepoData testrepodata= new RepoData();
		testrepodata.setKeyword("Java");
		assertEquals(testrepodata.getKeyword(),"Java");
	}
	
	
}
