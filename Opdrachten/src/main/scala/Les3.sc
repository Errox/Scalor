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