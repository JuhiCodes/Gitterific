package actors;

import actors.TimeActor.getNewData;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import controllers.CommitDetails;
import controllers.RepoDetails;
import models.Repository;
import utils.Util;
import play.libs.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
/**
 * This actor class manages commits
 * @author Juhi Patel
 */
public class CommitActor extends AbstractActor{

	public static Repository repos = new Repository();
	public static ActorRef commitActor;
	public CommitActor(ActorRef commitActor) {
		this.commitActor = commitActor;
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

	public static Props props(Repository r) {
		System.out.println("Inside props");
		repos = r;

		return Props.create(CommitActor.class, commitActor);
	}

	@Override
	public Receive createReceive() {
		System.out.println("Inside create recieve");
		return receiveBuilder()
				.match(Repository.class,message -> { 	 
					sender().tell(CommitDetails.findcommit(repos),self());
				})
				.build();
	}

}
