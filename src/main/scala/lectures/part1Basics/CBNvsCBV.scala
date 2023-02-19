package lectures.part1Basics

object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  def calledByName(x: => Long): Unit = { // Delays evaluation
    println("by name: " + x)
    println("by name: " + x)
  }

  calledByValue(System.nanoTime()) // Evaluated before call
  calledByName(System.nanoTime())  // Expression passed and evaluated every time


  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  //  printFirst(infinite(), 34) // stack overflow
  printFirst(34, infinite()) // works because infinite never evaluated
}
