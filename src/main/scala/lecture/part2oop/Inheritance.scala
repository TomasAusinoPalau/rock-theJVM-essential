package lecture.part2oop

object Inheritance extends App {

  // Single class inheritance
  class Animal {
    val creatureType = "wild"
    def eat = println("nomnomnom")
  }

  // Sub-class: Cat
  // Super-class: Animal
  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    override def eat = {
      super.eat
      println("woof woof")
    }
  }
  class Dog2(dogType: String) extends Animal {
    override val creatureType: String = dogType
  }
  val dog = new Dog("K9")
  dog.eat

  val dog2 = new Dog2("K9")

  println(dog.creatureType)
  println(dog2.creatureType)

  // type substitution (polymorphism)
  val unknownAnimal: Animal = new Dog("K9")

  unknownAnimal.eat

  // preventing overrides
  final class NonOverrideable
  //class Extension extends NonOverrideable


}
