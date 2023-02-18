package lectures.part1Basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }
  println(aFunction("hello", 42))

  def aParameterlessFunction: Int = 42
  println(aParameterlessFunction) // Function don't need to have parenthesis

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }
  println(aRepeatedFunction("hello", 3))
  // Recursive functions need return types! Simple functions can be inferred, but not recommended

  // WHEN you need loops, use recursion!

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a +b
    aSmallerFunction(n, n-1)
  }
  println(aBigFunction(10))

  /*
      Exercises:
      1.  A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
      2.  Factorial function 1 * 2 * 3 * .. * n
      3.  A Fibonacci function
          f(1) = 1
          f(2) = 1
          f(n) = f(n - 1) + f(n - 2)
      4.  Tests if a number is prime.
     */

  def greet(name: String, age: Int): String = {
    "Hi, my name is "+ name + " and I am " + age + " years old."
  }
  println(greet("Tim", 30))

  def factorialFunction(n: Int): Int = {
    if (n == 1) n
    else n * factorialFunction(n - 1)
  }
  println(factorialFunction(6))

  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }
  println(fibonacci(6)) // 0, 1, 1, 2, 3, 5, 8, 13

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)
    }
    isPrimeUntil(n / 2)
  }
  println(isPrime(71)) // should be true
  println(isPrime(70)) // should be false
}
