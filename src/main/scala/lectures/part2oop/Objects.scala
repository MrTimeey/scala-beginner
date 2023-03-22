package lectures.part2oop

object Objects extends App {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def from(mother: Person, father: Person): Person = new Person("Bobbie")
    def apply(mother: Person, father: Person): Person = new Person("Bobbie") // used in practise
  }
  class Person(val name: String) {
    // instance-level functionality

  }
  // COMPANIONS - class and object with same name

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = singleton instance
  val mary = Person
  val john = Person
  println(mary == john)

  val maryClass = new Person("Mary")
  val johnClass = new Person("John")
  println(maryClass == johnClass)

  val bobbie = Person.from(maryClass, johnClass)
  val bobbie2 = Person.apply(maryClass, johnClass)
  val bobbie3 = Person(maryClass, johnClass)

  // Scala applications = scala object with
  // def main(args: Array[String]): Unit


}
