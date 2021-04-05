// Drop, Swap, Recursief



// hint
// drop(list,0) => list
// drop(Nil,n) => Nil
// drop(list,n) => tail, n-1
def drop[A](l: List[A], amount: Int): List[A] =
  (l, amount) match {
    case (_, n) if n < 1 => l
    case (Nil, _) => Nil
    case (_::tail, n) => drop(tail, n-1)
  }

val l = List(1,2,3,4,5)
drop(l, -2)


// swap
// swap(Nil) => Nil
// swap(head::tail) => head swapped :: swap(tail)
def swap[A,B](l: List[(A,B)]): List[(B,A)] =
  l match {
    case Nil => Nil
    case (a,b)::tail => (b,a) :: swap(tail)
  }

val l2 = List((6,5), (4,2),(8,6))
swap(l2)


// swap
// swap(Nil) => Nil
// swap(head::tail) => head swapped :: swap(tail)
def swapTail[A,B](l: List[(A,B)], result: List[(B,A)] = Nil): List[(B,A)] =
  l match {
    case Nil => result
    case (a,b)::tail => swapTail(tail, result:+(b,a))
  }

val l3 = List((6,5), (4,2),(8,6))
swapTail(l3)