package lecture.part2oop

object Generics extends App {

  class MyList[A] {
    //use the type A
    def add[B >: A](element: B): MyList[B] = ???
  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  trait MyMap[Key, Value]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. COVARIANCE => List[Cat] extends List[Animal] is valid, accepts child classes
  class CovariantList[+A]
  val animal : Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? => will return CovariantList[Animal]


  // 2. INVARIANCE => Only the type that is written, it doesn't accept child classes
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // cannon't be of type Cat

  // CONTRAVARIANCE
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: Animal)
  val cage = new Cage(new Dog)

  //class Car
  //val newCage = new Cage(new Car)
}
