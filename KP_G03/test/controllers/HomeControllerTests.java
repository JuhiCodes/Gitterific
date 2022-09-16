package controllers;
//package controllers;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static play.mvc.Http.Status.OK;
//import static play.test.Helpers.*;
//
//import java.util.ArrayList;
//
//import controllers.HomeController;
//import org.junit.Test;
//import play.mvc.Result;
//import org.junit.Before;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import org.mockito.Mock;
//import models.Repository;
//
//import org.mockito.InjectMocks;
//
//
//public class HomeControllerTests {
//	
////	@InjectMocks
////    private RepoDetails repoDetails = new RepoDetails();
////	
////	@Mock
////	RepoDetails repoDetail;
////	
////	@Before
////	public void setup() {
////		RepoDetails repoMock = mock(RepoDetails.class);
////		when(repoMock.getRepoDetails()).thenReturn(new ArrayList<>());
////	}3
////	
////	@Test
////	public void testSave() {
////		assertEquals(new ArrayList<>(), repoDetails.getRepoDetails("angular"));
////	}
//	
//	@Test
//	public void testIndex() {
//		Result result = new HomeController().index();
//		assertEquals(OK, result.status());
//		assertEquals("text/html", result.contentType().get());
////		assertEquals("utf-8", result.charset().get());
////		assertTrue(contentAsString(result).contains("Welcome"));
//	}
//}
