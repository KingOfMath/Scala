import scala.reflect.runtime.universe._

trait Iter[T] {
  // abstract method
  def hasNext: Boolean

  def next(): T
}

class IntIter(end: Int) extends Iter[Int] {
  private var cur = 0

  override def hasNext: Boolean = cur < end

  override def next(): Int = {
    if (hasNext) {
      val temp = cur
      cur += 1
      temp
    } else 0
  }
}

trait Pet {
  val generalName: String
}

class Dog(name: String) extends Pet {
  override val generalName: String = name
}

// If a name is declared with the same name as trait
// It is automatically realized
class Dog2(var generalName: String = "ss")

trait t1 {
  val t = (a: Int) => a
}

trait t2 {
  def tt: Unit = {
    println("tt")
  }
}


object demo4_traits {

  def main(args: Array[String]): Unit = {
    //    val it = new IntIter(20)
    //    for (i <- 0 until 10; if i% 2 ==0){
    //      println(it.next())
    //    }

    //    val dog = new Dog("bb")
    //    val dog2 = new Dog2("aa")
    //    val arr = ArrayBuffer.empty[Pet]
    //    arr.append(dog)
    //    arr.append(dog2)
    //    arr.foreach(p => println(p.generalName))


    val d = new Dog2("gg")
    //      println(d.t(1))
    //      d.tt
    val field = d.getClass.getDeclaredField("generalName")
    field.setAccessible(true)
    val value = field.get(d).asInstanceOf[String]
    println(value)
  }

  def param[T](x: T)(implicit tag: TypeTag[T] /* WeakTypeTag*/) = {

    val targs = tag.tpe match {
      case TypeRef(_, _, args) => args
    }
    println(s"type of $x has type arguments $targs")
  }
}



