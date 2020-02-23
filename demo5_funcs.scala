object demo5_funcs {
  def main(args: Array[String]): Unit = {

    //  println(t1(new A).getClass)
    //  println(t2(1,2,3,4,5))

    // when used, function would be called
    lazy val res = t2(1,2,3)
  }

  // no return: declare return type
  def t1(a: A): A = {
    a.name = "gg"
    a
  }

  // multi-variables
  def t2(a: Int, args: Int*): Int = {
    println("start")
    var sum = a
    for (item <- args) {
      sum += item
    }
    sum
  }

  // => def t3() = { "ss" }
  def t3 = "ss"


}

class A {
  var name = ""
}
