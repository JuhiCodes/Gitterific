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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * This actor class manages user profiles
 * @author Krupali Bhatt
 */
public class UserActor extends AbstractActor{

	public static String loginName;
	public static ActorRef userActor;
	public UserActor(ActorRef userActor) {
	this.userActor = userActor;
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
    
    public static Props props(String login) {
    	System.out.println("Inside props");
    	loginName = login;
  
        return Props.create(UserActor.class, userActor);
    }
    
	@Override
	public Receive createReceive() {
		System.out.println("Inside create recieve");
		// TODO Auto-generated method stub
		return receiveBuilder()
   			 .match(String.class,message -> { 	 
   			 sender().tell(UserDetails.storeUserInfo(UserDetails.UserApiCall(loginName)),self());
   			 })
			 .build();
	}
	
}
