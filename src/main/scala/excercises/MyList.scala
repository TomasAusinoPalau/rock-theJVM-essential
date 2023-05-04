package excercises

abstract class MyList {

  // list of integers

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(n: Int): MyList
   def printElements: String
  override def toString: String ="[" + printElements + "]"

}

object Empty extends MyList {
  override def head: Int = throw new NoSuchElementException

  override def tail: MyList = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add(n: Int): MyList = new Cons(n, Empty)

  override def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  override def head: Int = h

  override def tail: MyList = t

  override def isEmpty: Boolean = false

  override def add(n: Int): MyList = new Cons(n, this)

  override def printElements: String = {
    if(tail.isEmpty) "" + h
    else h + " " + t.printElements
  }

}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.head)

  println(list.toString)
}