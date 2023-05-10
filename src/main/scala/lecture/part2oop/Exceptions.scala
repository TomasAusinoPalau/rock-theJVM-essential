package lecture.part2oop

object Exceptions extends App {

  val x: String = null

  //println(x.length)
  // this will crash NullPointerException

  // 1. throwing exceptions
  // def aWeirdValue(): Nothing = throw new NullPointerException("no int for you")

  // throwable classes extends the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withException: Boolean): Int =
    if(withException) throw new RuntimeException("No int for you!")
    else 41


  // Int + Unit = AnyVal
  val potentialFail: AnyVal = try {
    getInt(false)
  } catch {
    case e: NullPointerException => println("caught a Null Pointer exception")
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // code that will execute no matter what
    println("finally")
  }

  val potentialFail2: Int = try {
    getInt(false)
  } catch {
    case e: NullPointerException => 40
    case e: RuntimeException => 40
  } finally {
    // is an optional block, does not influence the return type of this expression
    // use for side effects
    println("finally")
  }


  // 3. define your own exceptions
  class OverflowException(msg: String) extends Exception
  class UnderflowException(msg: String) extends Exception
  class MathCalculationException(msg: String) extends Exception



  class PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = (x + y)
      if( x > 0 && y > 0 && result < 0) throw new OverflowException("exceeded max Int value")
      else if ( x < 0 && y < 0 && result > 0) throw new UnderflowException("exceeded min Int value")
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = (x - y)
      if( x > 0 && y < 0 && result < 0) throw new OverflowException("exceeded max Int value")
      else if ( x < 0 && y > 0 && result > 0) throw new UnderflowException("exceeded min Int value")
      else result
    }
    def multiply(x: Int, y: Int): Int = {
      val result = (x * y)
      if( x > 0 && y > 0 && result < 0) throw new OverflowException("exceeded max Int value")
      else if ( x < 0 && y < 0 && result < 0) throw new OverflowException("exceeded max Int value")
      else if ( x > 0 && y < 0 && result > 0) throw new UnderflowException("exceeded max Int value")
      else if ( x < 0 && y > 0 && result > 0) throw new UnderflowException("exceeded max Int value")
      else result
    }
    def divide(x: Int, y: Int): Int = {
      (x, y) match {
        case (x, y) if x == 0 || y == 0 => throw new MathCalculationException("Impossible to divide by 0")
        case _ => (x / y)
      }
    }
  }

  val calculator = new PocketCalculator

  println(calculator.add(4,4))

  println(calculator.subtract(0,4))

  println(calculator.multiply(4,4))

  println(calculator.divide(4,4))


}
