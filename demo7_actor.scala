import akka.actor.{Actor, ActorRef, ActorSystem, Props}

// extend Actor, override receive
class SayHelloActor extends Actor {

  // Receive: Called by Actor MailBox
  // type Receive = PartialFunction[Any, Unit]: Any type, no return
  // PartialFunction: case
  override def receive: Receive = {
    case "hi" => println("hi, received")
    case "exit" => {
      println("exit!")
      context.stop(self) // stop actorRef
      context.system.terminate() // stop system
    }
    case _ => println("wrong!")
  }
}

object sayHelloDemo {
  // ActorSystem: Create Actor
  private val actorFactory = ActorSystem("actorFactory")

  // Create an Actor and its ActorRef
  // 1) Props[sayHelloActor]: create an instance using scala.reflect
  // 2) "sayHello" is a name
  // 3) sayHelloActorRef: ActorRef is the actorRef of Props[SayHelloActor]
  private val sayHelloActorRef: ActorRef =
  actorFactory.actorOf(Props[SayHelloActor], "sayHello")

  def main(args: Array[String]): Unit = {

    sayHelloActorRef ! "hi"
    sayHelloActorRef ! "exit"

  }
}