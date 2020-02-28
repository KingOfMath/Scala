import scala.util.Try

class demo9_some {

}

object hhhhi {

  def toInt(s: String): Option[Int] = {
    try {
      Some(Integer.parseInt(s.trim))
    } catch {
      case e: Exception => None
    }
  }

  val sum = (x: Int, y: Int) => x + y

  def main(args: Array[String]): Unit = {

    //    toInt("1") match {
    //      case Some(i) => print(i)
    //      case None => print("wrong")
    //    }

    val bag = List("1", "2", "foo")
    bag.flatMap(toInt).foreach {
      i => println(i)
    }

    val x = sum(1, 1)
    val y = sum(2, 3)

    val z = for {
      a <- Try(x.toInt)
      b <- Try(y.toInt)
    } yield a * b
    val res = z.getOrElse(0) * 2

    print(res)
  }
}