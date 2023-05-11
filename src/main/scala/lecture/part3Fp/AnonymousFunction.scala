package lecture.part3Fp

object AnonymousFunction extends App {


  // anonymous function (LAMBDA)
  val doubler: Int => Int = x => x * 2

  println(doubler(2))

  // multiple paramenters
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  val justDoSomething: () => Int = () => 3

  println(justDoSomething)  // function itself
  println(justDoSomething()) // value

  // curly braces lambdas
  val stringToInt = { (string: String) =>
    string.toInt
  }

  val niceIncrementer: Int => Int = _ + 1 // equivalent x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // (a, b) => a + b
}
