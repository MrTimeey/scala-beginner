package lectures.part1Basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def trFact(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else trFact(n-1, n*acc)

  // val fact10 = trFact(10, 1) // acc is the same on each call - Wrap in Bigger function or use default
  val fact10 = trFact(10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")
  //savePicture(800, 600) // Leading parameters with defaults are confusing for compiler
  //savePicture(800) // confused because it can't figure out which parameter

  /*
  Solution:
  1. Pass leading arguments
  2. name the arguments
  */

  savePicture(width = 800) // named params
  savePicture(height = 600, width = 800, format = "bmp") // we can mixup
}
