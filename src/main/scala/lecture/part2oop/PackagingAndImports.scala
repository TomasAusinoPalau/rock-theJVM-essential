package lecture.part2oop

import lecture.part1basics.Recursion
import playground.{PrinceCharming, Cinderella => Princess}

import java.sql.{Date => SqlDate}
import java.util.Date

object PackagingAndImports extends App {

  val writer = new Writer("Tomi", "IDK", 2020)
  def fibonacciMethod(n: Int) = Recursion.fibonacci(n)


  // packages are in hierarchy
  // matching folder structures

  // package object
  sayHello
  println(SPEED_OF_LIGHT)


  val prince = new PrinceCharming
  val cinderella = new Princess

  val date = new Date()
  val sqlDate = new SqlDate(2018, 5, 4)

  // default imports
  // java.lang => String, Object, Exception, etc...
  // scala => Int, Nothing, Function
  // scala.Predef => println, ???
}


