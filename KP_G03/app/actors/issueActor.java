package actors;

import java.util.List;

import akka.actor.AbstractActor;
import akka.actor.Props;
import controllers.RepoIssues;
import models.Issues;
import models.Repository;

/**
 * This actor class manages issues of the repository
 * @author Parth Parekh
 */

public class issueActor extends AbstractActor{
	
	public static Props props() {
		return Props.create(issueActor.class);
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Repository.class, r -> {
			try {
				List<Issues> issueList = RepoIssues.getIssueList(r.getIssuesUrl());
				System.out.println("Issues :-");
				System.out.println(issueList);
	    		getSender().tell(issueList, getSelf());
			} catch(Exception ex) {
				getSender().tell(new akka.actor.Status.Failure(ex), getSelf());
				throw ex;
			}
		}).build();
	}

}
