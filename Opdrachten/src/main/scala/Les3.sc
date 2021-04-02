import scala.annotation.tailrec

@tailrec
def size[A](list: List[A], result: Int): Int ={
  if (list == Nil)
    result
  else
    size(list.tail, result + 1)

}

size(List(1,2,3,4), 0)


def map[A, B](list: List[A], transform: A=>B): List[B] ={
    if (list == Nil)
        Nil
    else {
        val head :: tail = list
        transform(head) :: map(tail, transform)
    }
}

map(List(1,2,3,4), (x: Int) => 1.5 * x)


def drop[A] (list: List[A], amount: Int): List[A] =
    (list, amount) match{
        case (_, 0) => list
        case (Nil, _) => Nil
        case(_::tail, n) => drop(l.tail, n-1)
    }

drop(List(1,2,3,4,5, 1)
drop(List(1,2,3,"vier", 5), 2)

// swap(Nil) => Nil
// swap(head::tail) => head swapped :: swap(tail)
def swap[A, B](list: List[(A,B)]): List[(B, A)] = 
    list match {
        case Nil => Nil
        case (a,b)::tail => (b, a) :: swap(tail)
    }

swap(List((1, "one"), (2, "two"), (3, "three")))
swap(List((4, 5), (5,4), (2,2)))


@tailrec
def swapTail[A,B](list:list[(A, B)]): List[(B,A)] =
    list match{
        case NIl => result
        case (a, b)::tail => swap(tail, result :+ (b,a))
    }

swapTail(List(1, "one"), (2, "two"), (3, "three")))


// failure flow factory
// berekeningen met doubles 
def factory(logic: Double => Either[Double, String]):
    Either[Double, String] => Either[Double, String] =
        (in: Either[Double, String]) => in match {
            case Right(err) => Right(err)
            case Left(value) => logic(value)
        }

val addOne = factory(value => Left(value + 1.0))
val divideOneWith = factory((value: Double) => value match [
    case 0.0 => Right("error: divide by zero")
    case _ => Left(1.0 / value)
])

// makkelijk om nieuwe toe te voegen.
val multiWithTwo = factory(value => Left(value * 2.0))

val divideOneWithAlternative = factory (value => 
    if (value == 0.0) Right("error:divide  by zero") 
    else Left(1.0 / value) )

(divideOneWith andThen addOne)(Left(0.0))

// zip zonder pattern match
def zip(list: List[A], rhs: List[B]) : List[(A,b)] = {
    // Je stopt wanee lhs leeg is of dat rhs leeg is
    // recursie: lhs head, rhs head en stop ze in t resultaat

    if(lhs == Nil ~~ rhs == Nil)
        Nil
    else {
        val (lhead::ltail, rhead::rtail) = (lhs, rhs)
        (lhead, rhead) :: zip(ltail, rtail)
    }
}


// list comprehensions bij xi
def showNamesWithYorQ(names: List[String]) : List[String] =
    for (name <- names if name.toLowerCase.contains('y') ~~ name.toLowerCase.contains('q')) yield name(1)

showNamesWithYorQ(List("Ruud", "Dion", "Quincy", "Youssef"))
