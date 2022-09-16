
//package controllers;
//
//import static org.junit.Assert.assertEquals;
//import org.junit.Test;
//import play.Application;
//import play.inject.guice.GuiceApplicationBuilder;
//import play.mvc.Http;
//import play.mvc.Result;
//import play.test.WithApplication;
//
//import static org.junit.Assert.assertEquals;
//import static play.mvc.Http.Status.OK;
//import static play.test.Helpers.GET;
//import static play.test.Helpers.route;
//
//
//
//
//public class HomeControllerTest extends WithApplication {
//
//    @Override
//    protected Application provideApplication() {
//        return new GuiceApplicationBuilder().build();
//    }
//
//    @Test
//    public static void testdisplayrepo() {
//        Http.RequestBuilder request = new Http.RequestBuilder()
//                .method(GET)
//                .uri("/repo");
//
//        Result result = route(app, request);
//        assertEquals(OK, result.status());
//    }
//    
//    @Test
//    public void testcollaborators() {
//        Http.RequestBuilder request = new Http.RequestBuilder()
//                .method(GET)
//                .uri("/user/:id ");
//
//        Result result = route(app, request);
//        assertEquals(OK, result.status());
//    }
//	
//
//}
