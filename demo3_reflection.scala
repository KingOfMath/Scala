

// change build.sbt

import scala.reflect.runtime.universe._

object demo3_reflection {

  val sum = (a: Int, b: Int) => a + b

  def param[T](x: T)(implicit tag: TypeTag[T] /* WeakTypeTag*/) = {

    val targs = tag.tpe match {
      case TypeRef(_, _, args) => args
    }
    println(s"type of $x has type arguments $targs")
  }

  class Foo(var name: String = "foolish")

  def main(args: Array[String]): Unit = {

    // object type

    val l = List(1, 2, 3)

    val foo = new Foo
    val field = foo.getClass.getDeclaredField("name")
    field.setAccessible(true)
    val value = field.get(foo).asInstanceOf[String]
    println(value)

  }
}
