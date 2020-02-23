import akka.actor.{ActorSystem, Props}

object demo8_actorCommunication {
  private val factory = ActorSystem("actorfactory")
  // B referred to A
  private val BActorRef = factory.actorOf(Props[BActor],"bActor")
  private val AActorRef = factory.actorOf(Props(new AActor(BActorRef)),"aActor")
  def main(args: Array[String]): Unit = {
    AActorRef ! "start"
  }
}
