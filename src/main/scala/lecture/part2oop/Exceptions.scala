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
    else 42


  try {
    getInt(true)
  } catch {
    case e: NullPointerException => println("caught a nullpointer exception")

  } finally {
    // code that will execute no matter what
    println("finally")
  }


}
