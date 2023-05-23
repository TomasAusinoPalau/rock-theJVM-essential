package lecture.part3Fp

object HOFAndCurries extends App {

  // val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = ???

  // function that applies a function n times over a value x
  val myApply: ((Int => Int), Int, Int) => Int = (method: (Int => Int), times: Int, n: Int) => {
    if(times <= 1) method(n)
    else myApply(method, times - 1, method(n))
  }
  def quadruplicate: Int => Int = _ * 5
  println(myApply(quadruplicate, 10, 30))

  def nTimes(method: (Int => Int), times: Int, n: Int): Int =
    if(times <= 1) method(n)
    else nTimes(method, times - 1, method(n))

  val plusOne = (n: Int) => n + 1
  println(nTimes(plusOne, 10, 1))
  println(myApply(plusOne, 10, 1))

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if(n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  }

  println(nTimesBetter(plusOne, 10)(1))

  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val addThree = superAdder(3)
  println(addThree(10))


  def toCurry[A, B](f: (A, A) => B): (A => A => B) =
    (x: A) => (y: A) => f(x, y)

  def fromCurry[A, B](f: A => A => B): (A, A) => B =
    (x: A, y: A) => f(x)(y)

  def compose[A, B, C](f: B => C, g: A => B): A => C =
    x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))
}
