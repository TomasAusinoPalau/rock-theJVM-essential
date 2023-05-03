package lecture.part1basics

object Expressions extends App {
  val x = 1 + 2 // EXPRESSION
  println(x)

  // Piece of code that can be evaluated to produce a value

  println(2 + 3 * 4)
  // + - * / & | ^ << >> >>> (right shift with zero extension)


  println(1 == x)
  // == != > >= < <=


  println(!(1 == x))
  // ! && ||


  var aVariable = 2
  aVariable += 3  // SIDE EFFECT
  println(aVariable)

  // Instructions (DO) vs Expressions (VALUE)

  // IF expression

  val aCondition = true
  val aConditionValue = if(aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionValue)

  // 1. "hello world" STRING EXPRESSION, println("hello world") SIDE EFFECT

  // 2. someValue = true, someOtherValue = 42

}
