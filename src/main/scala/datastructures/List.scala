package datastructures

trait List[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def add[S >:T](el: S): List[S]
  def ++[S >:T](l: List[S]): List[S]
  def filter[S >: T](pred: Predicate[S]): List[S]
}

trait Predicate[T] {
  def apply(el: T): Boolean
}

object Nil extends List[Nothing] {
  override def isEmpty: Boolean = true
  override def head: Nothing = throw new NoSuchElementException
  override def tail: List[Nothing] = throw new UnsupportedOperationException
  override def add[S](el: S): List[S] = ???
  override def ++[S](l: List[S]): List[S] = l
  override def filter[S](pred: Predicate[S]): List[Nothing] = Nil
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: Boolean = false
  override def add[S >: T](el: S): List[S] = new Cons(el, this)
  override def ++[S >:T](l: List[S]): List[S] = new Cons(head, tail ++ l)
  override def filter[S >: T](pred: Predicate[S]): List[S] = {
    if (pred.apply(head)) new Cons(head, tail filter pred)
    else tail filter pred
  }
}

object List {
  // TODO use tailrec
  def flatten[T](dpl: List[List[T]]): List[T] = {
    if (dpl.isEmpty) Nil
    else dpl.head ++ flatten(dpl.tail)
  }
}
