package exercises

abstract class MyList[+A] {

    /*
       head = first element of  the  list
       tail = remainder of the list
       isEmpty = is this list empty
       add(int) => new list with this element added
       toString => a string representation of the list
     */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  // polymorphic call
  def printElements: String
  override def toString: String = s"[${printElements}]"

  // higher order functions - Either receive functions as parameters or return other functions as result
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[Nothing] = Empty
  def filter(myPredicate: Nothing => Boolean): MyList[Nothing] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]) = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def filter(predicate: A => Boolean): MyList[A] =
    if(predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons[B](h, t ++ list)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)


  def printElements: String =
  if(t.isEmpty) s"${h}"
  else s"${h} ${t.printElements}"
}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head)
  println(list.tail.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)

  val listOfIntegers: MyList[Int] = Empty
  val listOfStrings: MyList[String] = Empty

  val listStrings = new Cons("Hello", new Cons("Tim", new Cons("From", Empty)))
  println(listStrings.tail.head)
  println(listStrings.tail.tail.head)
  println(listStrings.add("4").head)
  println(listStrings.isEmpty)
  println(listStrings.toString)


  println(list.map(new Function1[Int, String] {
    override def apply(a: Int): String = s"${a.toString}TIM"
  }).toString)


  println(list.map(new Function1[Int, Int] {
    override def apply(a: Int): Int = a * 2
  }).toString)

  println(list.filter(new Function1[Int, Boolean] {
    override def apply(element: Int): Boolean = element % 2 == 0
  }))

  println(list.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(element: Int): MyList[Int] = new Cons(element, new Cons(element + 1, Empty))
  }))
}
