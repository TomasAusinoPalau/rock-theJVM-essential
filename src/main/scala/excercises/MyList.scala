package excercises

abstract class MyList[+A] {

  // list of integers

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](n: B): MyList[B]
  def printElements: String
  override def toString: String ="[" + printElements + "]"

  def map[B >: A](transformer: A => B): MyList[B]
  def filter(condition: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: Nothing = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](n: B): MyList[B] = new Cons(n, Empty)

  override def printElements: String = ""

  override def map[B >: Nothing](transformer: Nothing => B): MyList[Nothing] = Empty

  override def filter(condition: Nothing => Boolean): MyList[Nothing] = Empty

  override def flatMap[B >: Nothing](transformer: Nothing => MyList[B]): MyList[Nothing] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](n: B): MyList[B] = new Cons(n, this)

  override def printElements: String = {
    if(tail.isEmpty) "" + h
    else h + " " + t.printElements
  }

  override def map[B >: A](transformer: A => B): MyList[B] =
    new Cons(transformer(head), tail.map(transformer))

  override def filter(condition: A => Boolean): MyList[A] = {
    if (condition(head)) new Cons(head, tail.filter(condition))
    else tail.filter(condition)
  }

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(head) ++ tail.flatMap(transformer)

  override def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)
}

case class EvenPredicate() extends ((Int) => Boolean) {
  override def apply(param: Int): Boolean = param % 2 == 1
}
case class IntToStringTransformer() extends ((Int) => String) {
  override def apply(param: Int): String = param.toString
}






object ListTest extends App {
  val listOfInt: MyList[Int] =  Cons(1, Cons(2, Cons(3, Empty)))
  val otherList: MyList[Int] = listOfInt ++ listOfInt
  val listOfStrings: MyList[String] = Empty

  val transformer: (Int => String) = IntToStringTransformer()

  println(listOfInt.map(_ * 2))
  println(listOfInt.filter(_ % 2 == 0))
  println((listOfInt ++ otherList).toString)
  println(listOfInt.flatMap((param: Int) => Cons(param, Cons(param + 1, Empty))))

  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))
}
