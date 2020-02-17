object demo2_class {

  def main(args: Array[String]): Unit = {
    val p = new Person
    p.name = "zhou"
    p.age = 14
    print(p.toString)
  }
}

class Person{
  val country: String = "china"
  var name: String = _
  var age: Int = _

  def canEqual(other: Any): Boolean = other.isInstanceOf[Person]

  override def equals(other: Any): Boolean = other match {
    case that: Person =>
      (that canEqual this) &&
        country == that.country &&
        name == that.name &&
        age == that.age
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(country, name, age)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def clone(): AnyRef = super.clone()

  override def toString = s"Person($country, $name, $age)"
}
