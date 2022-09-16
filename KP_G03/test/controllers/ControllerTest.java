package controllers;

import play.test.Helpers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.GET;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.POST;
import static play.test.Helpers.route;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import actors.KeywordSearchActor;
import controllers.HomeController;
import controllers.RepoDetails;

import org.junit.Before;

import models.Issues;
import models.Repository;
import play.Application;
import play.mvc.Http.RequestBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import actors.issueStatsActor;
import actors.repoCollabActor;
import actors.issueActor;
import actors.UserActor;
import actors.CommitActor;
import actors.TopicsActor;

/**This class tests controller
 *
 */
public class ControllerTest extends WithApplication {
	
	
	ActorSystem system = ActorSystem.create();
	
	/**
	 * This method creates a set up for testing
	 * @author Parth Parekh
	 */
	@Before
	public void setup() {
	   List<Repository> repos = new ArrayList<Repository>();
	   Repository r = new Repository();
	   r.setId("189491745");
	   r.setLogin("lyft");
	   r.setRepoName("flinkk8soperator");
	   r.setGitCommitsurl("https://api.github.com/repos/lyft/flinkk8soperator/commits{/sha}");
	   r.setCommitsUrl("https://api.github.com/repos/lyft/flinkk8soperator/commits{/sha}");
	   r.setIssuesUrl("https://api.github.com/repos/lyft/flinkk8soperator/issues{/number}");
	   r.setContributorURL("https://api.github.com/repos/lyft/flinkk8soperator/contributors");
	   
	   repos.add(r);
	   RepoDetails.repos = repos;
	   
	   List<String> repoCollabs = new ArrayList<String>();
	   repoCollabs.add("chandran");
	   repoCollabs.add("rsheik");
	   
	   List<Issues> issueList = new ArrayList<Issues>();
	   Issues i = new Issues();
	   i.setTitle("This is the issue the");
	   issueList.add(i);
	   HomeController.issueList = issueList; 
	}
	
	/**
	 * This method tests the create controller
	 * @author Parth Parekh
	 */	
	@Test
    public void testCreateController() {
        RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/search");

        Result result = route(app, request);
        assertEquals(OK, result.status());
	}
	   
   /**
	 * This method tests the issues
	 * @author Parth Parekh
	 */
   @Test
   public void testIssues() {
       RequestBuilder request = Helpers.fakeRequest()
               .method(GET).uri("/issues/189491745");

       Result result = route(app, request);
       assertEquals(OK, result.status());
   }
   
   /**
	 * This method tests the IssueStats
	 * @author Parth Parekh
	 */
   @Test
   public void testIssueStats() {
	   RequestBuilder request = Helpers.fakeRequest()
               .method(GET).uri("/issueStats");

       Result result = route(app, request);
       assertEquals(OK, result.status());
       assertEquals("text/html", result.contentType().get());
       assertEquals("utf-8", result.charset().get());
   }
   
   /**
	 * This method tests the commits
	 * @author Juhi Patel
	 */
   @Test
 public void testCommits() {
     RequestBuilder request = Helpers.fakeRequest()
             .method(GET).uri("/commits/189491745");

     Result result = route(app, request);
     assertEquals(OK, result.status());
 }
  
   /**
	 * This method tests the commitstats
	 * @author Juhi Patel
	 */
   @Test
   public void testCommitStats() {
	   RequestBuilder request = Helpers.fakeRequest()
               .method(GET).uri("/commitstats");

       Result result = route(app, request);
       assertEquals(OK, result.status());
       assertEquals("text/html", result.contentType().get());
       assertEquals("utf-8", result.charset().get());
   }
   
   /**
	 * This method tests the user
	 * @author Krupali Bhatt
	 */
   @Test
   public void testUser() {
       RequestBuilder request = Helpers.fakeRequest()
               .method(GET).uri("/user/lyft");

       Result result = route(app, request);
       assertEquals(OK, result.status());
   }
    
    @Test
    public void testSearch() {
    	
    	final TestKit testProbe = new TestKit(system);
    	final ActorRef keywordSearchActor = system
				.actorOf(KeywordSearchActor.props(testProbe.getRef()));
		
    	final ObjectMapper mapper = new ObjectMapper();
		final ObjectNode request = mapper.createObjectNode();
		request.set("keyword", mapper.convertValue("lstm", JsonNode.class));
		keywordSearchActor.tell(request, ActorRef.noSender());
		testProbe.expectMsgClass(ObjectNode.class);
    }
    
    @Test
    public void testIssueStatsSearch() {
    	
	   final TestKit testProbe = new TestKit(system);
	   final ActorRef issueStatsActorRef = system
				.actorOf(issueStatsActor.props());
		
	   List<Issues> issueList = new ArrayList<Issues>();
	   Issues i = new Issues();
	   i.setTitle("There is an issue..");
	   issueList.add(i);
	   issueStatsActorRef.tell(issueList, testProbe.getRef());
	   testProbe.expectMsgClass(Map.class);
    }
    
    @Test
    public void testIssueSearch() {
	   final TestKit testProbe = new TestKit(system);
	   final ActorRef issueActorRef = system
				.actorOf(issueActor.props());
     	
	   Repository r = new Repository();
	   r.setId("189491745");
	   r.setLogin("lyft");
	   r.setRepoName("flinkk8soperator");
	   r.setGitCommitsurl("https://api.github.com/repos/lyft/flinkk8soperator/commits{/sha}");
	   r.setCommitsUrl("https://api.github.com/repos/lyft/flinkk8soperator/commits{/sha}");
	   r.setIssuesUrl("https://api.github.com/repos/lyft/flinkk8soperator/issues{/number}");
	   r.setContributorURL("https://api.github.com/repos/lyft/flinkk8soperator/contributors");
   
       issueActorRef.tell(r, testProbe.getRef());
       testProbe.expectMsgClass(List.class);
     }
    
    /**
  	 * This method tests the Collaborators of repository implemented by Actors
  	 * @author Jay Patel
  	 */
    
    @Test
    public void testRepoCollabs() {
	   final TestKit testProbe = new TestKit(system);
	   Repository r = new Repository();
	   r.setId("189491745");
	   r.setContributorURL("https://api.github.com/repos/lyft/flinkk8soperator/contributors");
	   final ActorRef repoCollabActorRef = system
				.actorOf(repoCollabActor.props());
	   repoCollabActorRef.tell(r, testProbe.getRef());
     }
    
    @Test
    public void testUserAct() {
    	String login="lyft";
    	final TestKit testProbe = new TestKit(system);
    	final ActorRef userActorRef = system
				.actorOf(UserActor.props(login));
    	
     
       userActorRef.tell(login, ActorRef.noSender());
    }
    
    @Test
    public void testCommit() {
    	Repository r = new Repository();
  	   r.setId("189491745");
  	   r.setCommitsUrl("https://api.github.com/repos/lyft/flinkk8soperator/commits{/sha}");

  		
    	final TestKit testProbe = new TestKit(system);
    	final ActorRef commitActorRef = system
				.actorOf(CommitActor.props(r));
    	
       
       commitActorRef.tell(r, ActorRef.noSender());
      
    }
    

    /**
  	 * This method tests the Topics
  	 * @author Pal Patel
  	 */   
    @Test
    public void testTopics() {
    	
    	String tword="java";
    	final TestKit testProbe = new TestKit(system);
    	final ActorRef topicsearchActor = system
    			.actorOf(TopicsActor.props(tword));
    	
    	topicsearchActor.tell(tword, ActorRef.noSender());
    }
       
    
  
}
