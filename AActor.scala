import akka.actor.{Actor, ActorRef}

class AActor(actorRef : ActorRef) extends Actor{

  val BActorRef : ActorRef = actorRef
  var ACount : Int = 0

  override def receive: Receive = {
    case "A exit" =>
      println("A exit!")
      context.stop(self)
      context.system.terminate()
    case "start" =>
      println("A start!")
      self ! "Send to A!"
    case "Send to A!" =>
      // send to B
      // need BActorRef
      ACount += 1
      if (ACount == 5)
        self ! "A exit"
      println("A => B")
      Thread.sleep(500)
      BActorRef ! "B runs"


  }
}
