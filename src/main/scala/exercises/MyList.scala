package exercises

import lectures.part2oop.Generics.MyList

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
  def length: Int

  // higher order functions - Either receive functions as parameters or return other functions as result
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  // hofs
  def forEach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(f: (B, A) => B): B

  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""
  def length = 0

  def map[B](transformer: Nothing => B): MyList[Nothing] = Empty
  def filter(myPredicate: Nothing => Boolean): MyList[Nothing] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[Nothing] = Empty

  def forEach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty
  }
  def fold[B](start: B)(f: (B, Nothing) => B): B = start

  def ++[B >: Nothing](list: MyList[B]) = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def length: Int = 1 + t.length

  def filter(predicate: A => Boolean): MyList[A] =
    if(predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons[B](h, t ++ list)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def forEach(f: A => Unit): Unit = {
    f(head)
    t.forEach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0 ) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail);
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (length != list.length) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  def fold[B](start: B)(f: (B, A) => B): B =
    t.fold(f(start, h))(f)

  def printElements: String =
  if(t.isEmpty) s"${h}"
  else s"${h} ${t.printElements}"
}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfIntegers = new Cons(2, new Cons(4, new Cons(6, Empty)))
  println(list.tail.head)
  println(list.tail.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)

  val listOfStrings: MyList[String] = Empty

  val listStrings = Cons("Hello", Cons("Tim", Cons("From", Empty)))
  println(listStrings.tail.head)
  println(listStrings.tail.tail.head)
  println(listStrings.add("4").head)
  println(listStrings.isEmpty)
  println(listStrings.toString)


  println(list.map(a => s"${a.toString}TIM").toString)
  println(list.map(elem => elem * 2).toString)
  println(list.map(_ * 2).toString)

  println(list.filter(elem => elem % 2 == 0).toString)
  println(list.filter(_ % 2 == 0).toString)

  println(list.flatMap(elem => Cons(elem, Cons(elem + 1, Empty))).toString) // no underscore because of multiple usage of elem

  println("HOFsCurries")
  println(list.length)
  list.forEach(println)
  println(list.sort((x, y) => y - x))
  println(list.zipWith(list, (x, y) => y + x))
  println(list.zipWith(listOfIntegers, (x, y) => y + x))
  println(list.zipWith(listStrings, (x, y) => y + "-" + x))
  //println(list.zipWith(new Cons(1, listOfIntegers), (x, y) => y + x)) // Exception
  println(list.fold(0)((x, y) => x + y))
}
