package lecture.part2oop

object CaseClasses extends App {
  /*
    equals, hashCode, toString
   */

  case class Person(name: String, age: Int)
  // 1. class parameters are FIELDS
  val jim = new Person("Jim", 44)
  println(jim.name)

  // 2. have toString representation differently
  println(jim.toString)

  case object UnitedKingdom {
    def name: String = "UK innit"
  }

  // Expand MyList -> use case classes and case objects


}
