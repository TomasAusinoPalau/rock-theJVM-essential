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


}
