package actors;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import controllers.RepoIssues;
import models.Issues;
import models.Repository;

/**
 * This actor class computes issue statistics
 * @author Parth Parekh
 */
public class issueStatsActor extends AbstractActor{
	
	public static Props props() {
		return Props.create(issueStatsActor.class);
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder().match(List.class, issueList -> {
			System.out.println(issueList);
			List<String> issueTitles = ((List<Issues>)issueList).stream().map(i -> i.getTitle()).collect(Collectors.toList());
        	System.out.println("This are issue titles");
        	List<String> allWords = issueTitles.stream().flatMap(i -> Arrays.stream(i.split(" "))).collect(Collectors.toList());
        	
        	Map<String, Long> finalMapDescendingOrder = new LinkedHashMap<>();
        	
        	allWords.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
        	.sorted(Map.Entry.<String, Long>comparingByValue().reversed()).forEachOrdered(e -> finalMapDescendingOrder.put(e.getKey(), e.getValue()));
        	
        	getSender().tell(finalMapDescendingOrder, getSelf());
		}).build();
	}
}
