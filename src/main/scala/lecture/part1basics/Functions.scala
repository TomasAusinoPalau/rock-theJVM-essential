package lecture.part1basics

import scala.annotation.tailrec

object Functions extends App {

  def aFunction(a: String, b: Int): String =
    a + " " + b

  println(aFunction("hello", 2))

  def aRepeatedFunction(a: String, n: Int): String = {
    if(n == 1) a
    else a + aRepeatedFunction(a, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  def greetings(name: String, age: Int): String = {
    s"Hi, my name is $name and I'm $age years old."
  }

  println(greetings("tomi", 23))

  def factorial(n: Int, acc: Int = 1): Int = {
    if(n == 0) acc
    else factorial(n-1, acc * n)
  }

  println("the factorial number of 5 is " + factorial(5))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibHelper(n: Int, prev: Int, cur: Int): Int = {
      if (n <= 1) cur
      else fibHelper(n - 1, cur, prev + cur)
    }

    fibHelper(n, 0, 1)
  }

  println("fibo " + fibonacci(12))


  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(n / 2)
  }

  println("isPrime 37: " + isPrime(37))
  println("isPrime 2003: " + isPrime(2003))

}
