package lecture.part2oop

object Objects extends App {

  object Person { // type  + its only instance
    // "class" level functionality
    val N_EYES = 2
    def canfly: Boolean = false

    // FACTORY METHOD
    def from(mother:Person, father: Person): Person = new Person("bobby")
    def apply(mother:Person, father: Person): Person = new Person("bobby")

  }
  class Person(val name: String) {
    // instance level functionality
  }
  // COMPANIONS

  println(Person.N_EYES)
  println(Person.canfly)

  val mary = new Person("mary")
  val john = new Person("john")
  println(mary == john)

  val marySingleton = Person
  val johnSingleton = Person
  println(marySingleton == johnSingleton)

  val bobby = Person.from(mary, john)
  val bobbyApply = Person(mary, john)


}
