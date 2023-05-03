package lecture.part2oop

object OOBasics extends App {

  val person = new Person("tomi", 23)
  println(person.age)
  println(person.x)

  person.greet("Daniel")
  person.greet()




  val edgarAllanPoe: Writer = new Writer("Edgar Allan", "Poe", 1809)
  val theRaven: Novel = new Novel("The Raven", 1845, edgarAllanPoe)
  val theRavenV2: Novel =  theRaven.apply(1999)

  println(edgarAllanPoe.fullName())
  println(theRaven.authorAgeWhenReleased())
  println(theRaven.isWrittenBy())
  println(theRavenV2.authorAgeWhenReleased())

}

// constructor
class Person(name: String, val age: Int) {
  val x = 2

  println(1+3)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")
  // overloading same method names, with different signature
  def greet(): Unit = println(s"Hi, I'm $name")


  //multiple constructors (also overloading)
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")

}

// class PARAMETERS are not fields, for example [ name: String ]
// class FIELDS is accesible by keyword val, for example [ val age: Int ]


/*
  Novel and a Writer

  Writer: FirstName, Surname, yearOfBirth
    - method fullName

  Novel: name, yearOfRelease, author: Writer
    - method authorAge (at year of release)
    - isWrittenBy => author
    - copy (new year of release) new instance of novel
 */

class Writer(firstName: String, surname: String, val yearOfBirth: Int) {
  def fullName(): String = s"$firstName $surname"
}

class Novel(title: String, yearOfRelease: Int, author: Writer) {
  def apply(yearOfNewVersion: Int) = new Novel(title, yearOfNewVersion, author)


  def authorAgeWhenReleased(): Int = yearOfRelease - author.yearOfBirth
  def isWrittenBy(): String = author.fullName()
}
