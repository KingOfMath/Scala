package akka.actor

import akka.actor.messages.{Done, GiveMeRandomNumber, Start}

import scala.util.Random._

class demo9_akkaCommunication {

}


object messages {

  case class Done(randomNumber: Int)

  case object GiveMeRandomNumber

  case class Start(actorRef: ActorRef)

}

class RandomNumberActor extends Actor {
  override def receive: Receive = {
    case GiveMeRandomNumber =>
      println("message!")
      val randomNumber = nextInt
      sender ! Done(randomNumber)
  }
}

class QueryActor extends Actor {
  override def receive: Receive = {
    case Start(actorRef) =>
      println(s"send me the next number")
      actorRef ! GiveMeRandomNumber
    case Done (randomNumber) =>
      println (s"received a random number $randomNumber")
  }
}

object communication extends App{
  val actorSystem = ActorSystem("hiAkka")
  val randomNumberActor =
    actorSystem.actorOf(Props[RandomNumberActor],
    "randomNumberActor")
  val queryActor =
    actorSystem.actorOf(Props[QueryActor],
    "queryActor")
  queryActor ! Start(randomNumberActor)
}