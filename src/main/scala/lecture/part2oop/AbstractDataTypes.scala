package lecture.part2oop

object AbstractDataTypes extends App {

  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat: Unit = println("ñomñom")
  }

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile
    extends Animal
    with Carnivore
    with ColdBlooded {
    override val creatureType: String = "Reptile"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s"I'm a creoc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile

  croc.eat(dog)
}
