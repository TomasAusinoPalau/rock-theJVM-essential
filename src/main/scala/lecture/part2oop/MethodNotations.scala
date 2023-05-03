package lecture.part2oop

object MethodNotations extends App {

  class Person(val name: String, favouriteMovie: String) {

    def likes(movie: String): Boolean = movie == favouriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

  }


  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // is equivalent
  //infix notation = operator notation

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom) // method acts as an operator between the strings
  println(mary + tom)
  println(1 + 2)




  // ALL OPERATORS ARE METHODS.
}
