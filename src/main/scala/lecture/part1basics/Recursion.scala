package lecture.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
     if(n <= 1) 1
     else {
       println("factorial of n " + n + " but first i need factorial of " + (n-1))
      val result = n * factorial(n - 1)
       println("computed factorial of " + n )

       result
     }


  }
  //factorial(10)

  def anotherFactorial(n: Int): BigInt = {
    def factorialHelper(x: Int, acc: BigInt): BigInt = {
      if(x <= 1) acc
      else factorialHelper(x - 1, x * acc)
    }
    factorialHelper(n, 1)
  }

  println(anotherFactorial(1))

  def concatenate(word: String, times: Int): String = {
    @tailrec
    def concatenateHelper(value: String = "", remaining: Int): String =
      if(remaining <= 1) value + word
      else concatenateHelper(word + word, remaining - 1)

    concatenateHelper("", times)
  }

  println(concatenate("tomi", 2))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean =
      if(t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(n / 2)
  }

  println("isPrime? 12 " + isPrime(29))

  def fibonacci(n: Int): Int = {
    def fibonacciHelper(i: Int, last: Int, nextToLast: Int): Int = {
      if(i >= n) last
      else fibonacciHelper(i + 1, last + nextToLast, last)
    }

    if (n <= 2) 1
    else fibonacciHelper(2, 1,1)
  }

  println(fibonacci(8))

}
