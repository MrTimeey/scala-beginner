package lectures.part1Basics

object Expressions extends App {

  val x = 1 + 2 // Expression
  println(x)

  println(2 + 3 * 4) // mathematical expression
  // + - * / & | ^ << >> >>>

  println(1 == x) // boolean expression
  // == != > >= < <=

  println(!(1 == x)) // logical operation
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ....... side effects
  println(aVariable)

  // Instructions (DO something) vs Expressions (Value)

  // IF Condition
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // If Expression, not instruction
  println(aConditionedValue)
  println(if(aCondition) 5 else 3) // IF is a Expressions!
  println(1 + 3)

  var i = 0
  while (i < 10) {
    println("loop " + i)
    i += 1
  }
  // NEVER DO LOOPS

  // EVERYTHING IN SCALA IS A EXPRESSION

  val aWeirdValue = (aVariable = 3) // Unit === void
  println(aWeirdValue) // Prints: ()

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

  // Not possible:
  // val anotherValue = z + 1

  //  1. difference between "hello world" vs println("hello world")?
  // String and Unit
  // 2.
  val someValue = {
    2 < 3
  }
  // -->  boolean true
  val someOtherValue = {
    if (someValue) 239 else 986
    42
  }
  // --> 42
}
