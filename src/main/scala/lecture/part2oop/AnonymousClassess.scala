package lecture.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }
  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hgfgfg")
  }

  println(funnyAnimal.getClass)

  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("hgfgfg")
  }

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, I'm $name, how can I help you?")
  }

  val jim = new Person("jim") {
    override def sayHi: Unit = println(s"Hi, I'm Jim, how can I help you?")
  }


}
