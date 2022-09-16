package actors;

import actors.TimeActor.getNewData;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import controllers.CommitDetails;
import controllers.RepoDetails;
import controllers.UserDetails;
import models.Repository;
import utils.Util;
import play.libs.Json;
import controllers.RepoTopics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * This actor class manages topics.
 * @author Pal Patel
 */
public class TopicsActor extends AbstractActor{

	public static String word;
	public static ActorRef topicsActor;
	public TopicsActor(ActorRef topicsActor) {
	this.topicsActor = topicsActor;
	}
	
    @Override
    public void preStart() {
    	
       	context().actorSelection("/user/timeActor/")
                 .tell(new TimeActor.RegisterMsg(), self());
    }
    
    @Override
    public void postStop() {
    	
       	context().actorSelection("/user/timeActor/")
                 .tell(new TimeActor.DeRegisterMsg(), self());
    }
    
    
   public static Props props(String tword) {
    	System.out.println("Inside props");
    	word = tword;
  
        return Props.create(TopicsActor.class, topicsActor);
    }
    
	@Override
	public Receive createReceive() {
		System.out.println("Inside create recieve");
		// TODO Auto-generated method stub
		return receiveBuilder()
   			 .match(String.class,message -> { 	 
   			 sender().tell(RepoTopics.getRepoDetails(word),self());
   			 })
			 .build();
	}

	
}

