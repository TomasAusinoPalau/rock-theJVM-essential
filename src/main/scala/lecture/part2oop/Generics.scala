package lecture.part2oop

object Generics extends App {

  class MyList[A] {
    //use the type A
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

  // 1. List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal : Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ???


  // 2. INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]
}
