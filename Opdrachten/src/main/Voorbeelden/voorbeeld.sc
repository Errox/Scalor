import scala.annotation.tailrec
// drop, swap recursief met pattern matching

// hint, psst
// drop(list, 0) => list
// drop(Nil, n) => Nil
// drop(list, n) => tail, n-1
def drop[A](list: List[A], amount: Int): List[A] =
  (list, amount) match {
    case (_, n) if n < 1 => list
    case (Nil, _) => Nil
    case (_::tail, n) => drop(tail, n-1)
  }

drop(List(1,2,3,4,5), 6)

// swap
// swap(Nil) => Nil
// swap(head::tail) => head swapped :: swap(tail)
def swap[A, B](list: List[(A, B)]): List[(B, A)] =
  list match {
    case Nil => Nil
    case (a, b)::tail => (b, a) :: swap(tail)
  }

swap(List((1, "one"), (2, "two"), (3, "three")))
swap(List((4,5), (5,4), (2,2)))
@tailrec
def swapTail[A, B](list: List[(A, B)], result: List[(B, A)] = Nil): List[(B, A)] =
  list match {
    case Nil => result
    case (a, b)::tail => swapTail(tail, result :+ (b,a)) // oef, duur
  }

swapTail(List((1, "one"), (2, "two"), (3, "three")))

// failure flow factory
// berekeningen met doubles
def factory(logic: Double => Either[Double, String]):
  Either[Double, String] => Either[Double, String] =
  (in: Either[Double, String]) => in match {
    case Right(err) => Right(err)
    case Left(value) => logic(value)
  }

val addOne = factory(value => Left(value + 1.0))

val divideOneWith = factory((value: Double) => value match {
  case 0.0 => Right("Error: divide by zero")
  case _ => Left(1.0 / value)
})

// makkelijk om nieuwe toe te voegen
val multWithTwo = factory(value => Left(value * 2.0))

val divideOneWithAlternative = factory(value =>
  if (value == 0.0) Right("Error: divide by zero")
  else Left(1.0 / value) )

(divideOneWith andThen addOne)(Left(0.0))


// verzoeknummers

// zip zonder pattern match (zelf met doen!)
def zip[A,B](lhs: List[A], rhs: List[B]) : List[(A, B)] = {
  // stop: lhs is leeg, of rhs is leeg
  // recursie: lhs head, rhs head en stop ze in t resultaat

  if (lhs == Nil || rhs == Nil)
    Nil
  else {
    val (lhead::ltail, rhead::rtail) = (lhs, rhs)
    (lhead, rhead) :: zip(ltail, rtail)
  }
}

// list comprehensions bij xi
//xi.   Show all names with the letter y or q in them
def showNamesWithYOrQ(names: List[String]) : List[String] =
  for (name <- names if name.toLowerCase.contains('y') || name.toLowerCase.contains('q')) yield name

showNamesWithYOrQ(List("Ruud", "Dion", "Quincy", "Youssef"))
