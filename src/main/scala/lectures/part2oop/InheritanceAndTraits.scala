package lectures.part2oop

object InheritanceAndTraits extends App {

  // single class inheritance - one class at time
  sealed class Animal { // Superclass of Cat

    val creatureType = "wild"
    def eat = println("nomnom")
    //private def privateEat = println("nomnom") // not visible from cat
    // protected def protectedEat = println("nomnom")
  }

  class Cat extends Animal { // Subclass of Animal
    def crunch = {
      eat // protected can be call in method but not from outside
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch


  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    // override val creatureType: String = "domestic" // can be inline or in constructor
    override def eat: Unit = {
      super.eat
      println("crunch, crunch")
    } // Override can change visibility
  }
  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat // Method call will go to most overriden version whenever possible

  // preventing overrides
  // 1 - use final on member
  // 2 - use final on class
  // 3 - seal the class = extend classes in THIS FILE, prevents extention in other files

}
