package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * This actor class manages repositories
 * @author Jay Patel
 */
public class repoActor extends AbstractActor{
	
	private final ActorRef webSocket;
	
	public repoActor(ActorRef ws) {
		this.webSocket = ws;
	}
	
    public static Props props(ActorRef webSocket) {
        return Props.create(repoActor.class, webSocket);
    }
	
	@Override
	public Receive createReceive() {
		// TODO Auto-generated method stub
		return receiveBuilder()
	   			 .match(ObjectNode.class, s -> repoData(s))
				 .build();
	}
	
	public void repoData(ObjectNode o) {
		System.out.println("here");
		System.out.println(o);
	}
	
}
