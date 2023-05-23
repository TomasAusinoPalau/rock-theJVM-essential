package excercises

abstract class MyList[+A] {

  // list of integers

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](n: B): MyList[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B >: A](transformer: A => B): MyList[B]

  def filter(condition: A => Boolean): MyList[A]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  // concatenation
  // test comment
  def ++[B >: A](list: MyList[B]): MyList[B]

  def foreach(transformer: A => Unit): Unit
  def sort(sorter: (A, A) => Int): MyList[A]
  def zipWith[B >: A](list: MyList[B], zipper: ((A, B) => B)): MyList[B]
  def fold[B](start: B)(operator: (B, A)=> B): B
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

  override def foreach(transformer: Nothing => Unit): Unit = ()
  override def sort(sorter: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  override def zipWith[B >: Nothing](list: MyList[B], zipper: (Nothing, B) => B): MyList[B] = list
  override def fold[B](start: B)(operator: (B, Nothing)=> B): B = start

}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h

  override def tail: MyList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](n: B): MyList[B] = Cons(n, this)

  override def printElements: String = {
    if(tail.isEmpty) "" + h
    else h + " " + t.printElements
  }

  override def map[B >: A](transformer: A => B): MyList[B] =
    Cons(transformer(head), tail.map(transformer))

  override def filter(condition: A => Boolean): MyList[A] = {
    if (condition(head)) Cons(head, tail.filter(condition))
    else tail.filter(condition)
  }

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(head) ++ tail.flatMap(transformer)

  override def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

  override def foreach(transformer: A => Unit): Unit = {
    if(tail == Empty) transformer(this.h)
    else {
      transformer(h)
      tail.foreach(transformer)
    }
  }

  override def sort(sorter: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if(sortedList.isEmpty) Cons(x, Empty)
      else if (sorter(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(sorter)
    insert(h, sortedTail)
  }

  override def zipWith[B >: A](list: MyList[B], zipper: (A, B) => B): MyList[B] = {
      if(list.tail == Empty) Cons(zipper(this.h, list.head), this.t)
      else Cons(zipper(this.h, list.head), tail.zipWith(list.tail, zipper))
  }

  override def fold[B](start: B)(operator: (B, A) => B): B = {
    tail.fold(operator(start, h))(operator)
  }

}

case class EvenPredicate() extends ((Int) => Boolean) {
  override def apply(param: Int): Boolean = param % 2 == 1
}
case class IntToStringTransformer() extends ((Int) => String) {
  override def apply(param: Int): String = param.toString
}

object ListTest extends App {
  val listOfInt: MyList[Int] =  Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
  val otherList: MyList[Int] = listOfInt ++ listOfInt
  val listOfStrings: MyList[String] = Empty

  val transformer: (Int => String) = IntToStringTransformer()

  println(listOfInt.map(_ * 2))
  println(listOfInt.filter(_ % 2 == 0))
  println((listOfInt ++ otherList).toString)
  println(listOfInt.flatMap((param: Int) => Cons(param, Cons(param + 1, Empty))))

  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))
  listOfInt.foreach(println)

  val zipper: (Int, Int) => Int = (x: Int, y: Int) => x * y


  println(listOfInt.sort((x, y) => y - x))


  println((listOfInt ++ listOfInt).zipWith(listOfInt ++ listOfInt, zipper))
}
