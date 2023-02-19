package lectures.part1Basics

import scala.annotation.tailrec
import scala.jdk.Accumulator

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n == 1) n
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n-1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }

  println(factorial(10))


  // println(factorial(50000)) --> Exception in thread "main" java.lang.StackOverflowError

  def anotherFactorial(n: Int): BigInt = {
    @tailrec // tells compiler function should be tail recursive and throws error if not
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL RECURSION
    // recursive call last statement so scala can optimize - Uses same Stackframe because not needed later
    factHelper(n, 1)
  }
  println(anotherFactorial(5000))
  println(anotherFactorial(20000))

  // WHEN YOU NEED LOOPS, USE TAIL RECURSION.


  /*
      Exercises:
      1.  Concatenate a string n times
      2.  IsPrime function tail recursive
      3.  Fibonacci function, tail recursive.
     */


  def aRepeatedFunction(aString: String, n: Int): String = {
    @tailrec
    def repeatHelper(n: Int, acc: String): String = {
      if (n == 1) acc
      else repeatHelper(n - 1, aString + acc)
    }
    repeatHelper(n, "")
  }

  println(aRepeatedFunction("hello", 20))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailrec(n / 2, true)
  }

  println(isPrime(2003))
  println(isPrime(629))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fiboTailrec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fiboTailrec(2, 1, 1)
  }

  println(fibonacci(8)) // 1 1 2 3 5 8 13, 21
}
