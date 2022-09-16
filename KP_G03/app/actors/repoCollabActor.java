package actors;

import java.util.ArrayList;
import java.util.List;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.AbstractActor.Receive;
import controllers.RepoDetails;
import controllers.RepoIssues;
import models.Issues;
import models.Repository;

/**
 * This actor class manages Repository Collabs
 * @author Jay Patel
 */
public class repoCollabActor extends AbstractActor{

	public static Props props() {
		return Props.create(repoCollabActor.class);
	}
	
	@Override
	public Receive createReceive() {
		// TODO Auto-generated method stub
		return receiveBuilder().match(Repository.class, r -> {
			try {
				ArrayList<String> repCollabs = RepoDetails.listCollabRepos(r.getContributorURL());
	    		getSender().tell(repCollabs, getSelf());
			} catch(Exception ex) {
				getSender().tell(new akka.actor.Status.Failure(ex), getSelf());
				throw ex;
			}
		}).build();
	}
}
