package actors;
import akka.japi.pf.DeciderBuilder;
import akka.actor.SupervisorStrategy;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.OneForOneStrategy;
import java.time.Duration;

/**
 * This actor class is a supervisorActor. It implementsfault tolerance strategy.
 * @author KP_G03
 */
public class SupervisorActor extends AbstractActor {

  private static SupervisorStrategy strategy =
      new OneForOneStrategy(
          10,
          Duration.ofMinutes(1),
          DeciderBuilder.match(ArithmeticException.class, e -> SupervisorStrategy.resume())
              .match(NullPointerException.class, e -> SupervisorStrategy.restart())
              .match(IllegalArgumentException.class, e -> SupervisorStrategy.stop())
              .matchAny(o -> SupervisorStrategy.escalate())
              .build());

  @Override
  public SupervisorStrategy supervisorStrategy() {
    return strategy;
  }


  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(
            Props.class,
            props -> {
              getSender().tell(getContext().actorOf(props), getSelf());
            })
        .build();
  }
}