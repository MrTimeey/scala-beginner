package lectures.part3fp

object AnonymousFunctions extends App {

  val doublerOld = new Function[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

  // anonymous function (LAMBDA)
  /*val doubler = (x: Int) => x * 2*/
  val doubler: Int => Int = x => x * 2


  // multiple parameters
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b // parentheses for multiple params

  // no params
  val justDoSomething: () => Int = () => 3

  println(justDoSomething)  // function itself
  println(justDoSomething()) // function called

  // curly braces - common style
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MORE syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent for (x: Int) => x + 1
  val niceAdder: (Int, Int) => Int = _ + _  // equivalent for (a:Int, b:Int) => a + b

  /*
     1.  MyList: replace all FunctionX calls with lambdas
     2.  Rewrite the "special" adder as an anonymous function
    */

  val superAdder = (x: Int) => (y: Int) => x + y
  println(superAdder(3)(4))
}
