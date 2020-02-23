import akka.actor.Actor

class BActor extends Actor{

  var BCount: Int = 0

  override def receive: Receive = {
    case "B exit" =>
      println("B exit!")
      context.stop(self)
      context.system.terminate()
    case "B runs" =>
      BCount += 1
      println("B => A")
      sender() ! "Send to A!"
      Thread.sleep(500)
      if (BCount == 4)
        self ! "B exit"
  }
}
