package lectures.part2oop

import java.lang.invoke.WrongMethodTypeException
import scala.annotation.tailrec

object OOBasics extends App {

  val person = new Person("Tom", 42)
  println(person)
  println(person.age)
  println(person.x)

  person.greet("Daniel")
  person.greet()

  val otherPerson = new Person("Karl")
  otherPerson.greet()

  val writer = new Writer("Tim", "Cruise", 1960)
  val imposter = new Writer("Tim", "Cruise", 1960)
  println(writer.fullName)
  val otherWriter = new Writer("Sebastian", "Flow", 1940)
  println(otherWriter.fullName)
  val novel = new Novel("Fancy Book", 2015, writer)
  println(s"Author age: ${novel.authorAge()}")
  println(s"Written by Tim: ${novel.isWrittenBy(writer)}")
  println(s"Written by imposter: ${novel.isWrittenBy(imposter)}")
  println(s"Written by Sebastian: ${novel.isWrittenBy(otherWriter)}")
  val copiedNovel = novel.copy(2020)
  println(s"Copied novel author age: ${copiedNovel.authorAge()}")

  val counter = new Counter(0)
  counter.print()
  counter.increment.print()
  counter.increment.increment.increment.increment.print()
  counter.increment(5).print()

  val otherCounter = new Counter(42)
  otherCounter.decrement.print()
  otherCounter.decrement.decrement.decrement.print()
  otherCounter.decrement(5).print()

}

// constructor
class Person(name: String, val age: Int = 0) { // defaults also work
  // all expressions in body block are executed each instanciation
  val x = 2 // field
  println(1 + 2)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  def this(name: String) = this(name, 0) // multiple constructors but they can only call other constructors

}

// class parameters are NOT FIELDS! Need val to access


/*
  FIRST EXERCISE
  Novel and a Writer

  Writer: first name, surname, yearOfBirth
    - method fullname

  Novel: name, year of release, author
  - authorAge
  - isWrittenBy(author)
  - copy (new year of release) = new instance of Novel
 */

class Writer(firstname: String, surname: String, val yearOfBirth: Int) {
  def fullName: String = s"$surname, $firstname"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge(): Int = yearOfRelease - author.yearOfBirth
  def isWrittenBy(author: Writer): Boolean = this.author == author
  def copy(yearOfRelease: Int): Novel = new Novel(name, yearOfRelease, author)
}

/*
  SECOND EXERCISE
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */

class Counter(val currentCount: Int) {
  def increment: Counter = {
    println("incrementing")
    new Counter(currentCount + 1) // immutability
  }
  def decrement: Counter = {
    println("decrementing")
    new Counter(currentCount - 1)
  }

  def increment(n: Int): Counter = {
    if (n <= 0) this
    else increment.increment(n - 1)
  }
  def decrement(n: Int): Counter = {
    if(n <= 0) this
    else decrement.decrement(n - 1)
  }

  def print(): Unit = println(currentCount)
}
