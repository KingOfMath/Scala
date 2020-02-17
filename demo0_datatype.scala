import scala.util.control.Breaks

object demo0_datatype {


  def main(args: Array[String]): Unit = {
    var a: Int = 12
    var ch: scala.Char = 'g'
    var str: String = "cc"
    //    test0()
    //    test1()
        print(test2(3,40))
    //    test3()
  }

  def test0(): Unit = {
    var a = true
    var b = false
    println(a && b)
  }

  def test1(): Unit = {
    for (i <- 0 until 100; if i % 2 == 0; if i % 3 == 0; if i % 4 == 0) {
      print(i + " ")
    }
    println()
    for (i <- 0 until 2; j <- 0 until 3) {
      print((i + j) + " ")
    }
  }

  def test2(x: Int, y: Int): Int = if (x>y) x else y

  def test3(): Unit = {
    var a = 0
    var b = 0
    val numList1 = List(1, 2, 3, 4, 5)
    val numList2 = List(11, 12, 13)
    val inter = new Breaks
    val outer = new Breaks
    outer.breakable {
      for (a <- numList1) {
        print("a:" + a + " ")
        if (a == 3)
          outer.break
        inter.breakable {
          for (b <- numList2) {
            if (b == 12) {
              println(b)
              inter.break
            }
          }
        }
      }
    }
  }

}