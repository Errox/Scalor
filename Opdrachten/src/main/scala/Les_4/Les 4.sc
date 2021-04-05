// Drop, Swap, Recursief

def drop[A](l: List[A], amount: Int): List[A] =
  (l, amount) match {
    case (_, n) if n < 1 => l
    case (Nil, _) => Nil
    case (_::tail, n) => drop(tail, 1)
  }