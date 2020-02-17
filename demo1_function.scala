object demo1_function {

  val sum = (a: Int, b: Int) => a + b

  def sum_math(a: Int)(b: Int) = a + b

  def main(args: Array[String]): Unit = {
    println(sum(1, 2)) // variable is also callable
    println(sum_math(4)(5))
    fun1
    println(fun2(3, 4))
    fun3("joker", "wayne")
    println(fac(10))
  }

  def fun1: Unit = {
    println("hi")
  }

  def fun2(a: Int, b: Int): Int = {
    a + b // 默认可以返回
  }

  def fun3(names: String*): Unit = {
    println(names)
  }

  def fac(c: Int): Int = if (c < 0) c else fac(c - 1)
}
