package excercises

abstract class MyList[+A] {

  // list of integers

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](n: B): MyList[B]
  def printElements: String
  override def toString: String ="[" + printElements + "]"

  def map[B >: A](transformer: MyTransformer[A, B]): MyList[B]
  def filter(condition: A => Boolean): MyList[A]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]] ): MyList[B]
  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: Nothing = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](n: B): MyList[B] = new Cons(n, Empty)

  override def printElements: String = ""

  override def map[B >: Nothing](transformer: MyTransformer[Nothing, B]): MyList[Nothing] = Empty

  override def filter(condition: Nothing => Boolean): MyList[Nothing] = Empty

  override def flatMap[B >: Nothing](transformer: MyTransformer[Nothing, MyList[B]]): MyList[Nothing] = Empty

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

  override def map[B >: A](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(head), tail.map(transformer))

  override def filter(condition: A => Boolean): MyList[A] = {
    if (condition(head)) new Cons(head, tail.filter(condition))
    else tail.filter(condition)
  }

  override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(head) ++ tail.flatMap(transformer)

}

trait MyPredicate[-T] {
  def test(param: T): Boolean
}
trait MyTransformer[-A, +B] {
  def transform(param: A): B
}

case class EvenPredicate() extends MyPredicate[Int] {
  override def test(param: Int): Boolean = param % 2 == 1
}
case class IntToStringTransformer() extends MyTransformer[Int, String] {
  override def transform(param: Int): String = param.toString
}






object ListTest extends App {
  val listOfInt: MyList[Int] =  Cons(1, Cons(2, Cons(3, Empty)))
  val otherList: MyList[Int] = listOfInt ++ listOfInt
  val listOfStrings: MyList[String] = Empty

  val transformer: MyTransformer[Int, String] = IntToStringTransformer()

  println(listOfInt.map(transformer))
  println(listOfInt.filter(_ % 2 == 0))
  println((listOfInt ++ otherList).toString)
  println(listOfInt.flatMap(new MyTransformer[Int, MyList[Int]]() {
    override def transform(param: Int): MyList[Int] = Cons(param, Cons(param + 1, Empty))
  }))
}
