package lectures.part1Basics

object ValuesVariablesTypes extends App {

  val x: Int = 42
  println(x)

  //x = 2  // VALs are immutable

  val y = 42 // Types are optional - Compiler can infer types
  println(y)

  val aString: String = "Hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = true
  val aCharacter: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613 // Short is half int
  val aLong: Long = 51214654646544L // Doubled int
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables
  var aVariable: Int = 4

  aVariable = 5 // side effects

  // Programs without side effects are easier to understand!


}
