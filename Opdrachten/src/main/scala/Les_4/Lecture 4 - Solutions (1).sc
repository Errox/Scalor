// Exercise 2
// i
def firstElement(l: List[Int]): Option[Int] = l match {
  case Nil => None
  case head::_ => Some(head)
}

firstElement(List(1,2,3,4))
firstElement(List())


// ii
def maxFromOption(lhs: Int, rhsOpt: Option[Int]): Option[Int] = rhsOpt match {
  case None => None
  case Some(rhs) => Some(lhs max rhs)
}

def largestElement(l: List[Int]): Option[Int] = l match {
  case Nil => None
  case head :: Nil => Some(head)
  case head :: tail => maxFromOption(head, largestElement(tail))
}

largestElement(List(1,2,3,4))
largestElement(List())


// iii
def concatenateStringOption(lhs: String, rhsOpt: Option[String]): Option[String] = rhsOpt match {
  case None => None
  case Some(rhs) => Some(lhs + rhs)
}

def replicateString(strOpt: Option[String], numOpt: Option[Int]): Option[String] =
  (strOpt, numOpt) match {
    case (_, None) => None
    case (None, _) => None
    case (_, Some(0)) => Some("")
    case (_, Some(num)) if num < 0 => None
    case (Some(str), Some(num)) => {
      val tailString: Option[String] = replicateString(Some(str), Some(num - 1))
      concatenateStringOption(str, tailString)
    }
  }

replicateString(Some("Avans"), Some(3))
replicateString(None, Some(3))
replicateString(Some("Avans"), None)
replicateString(Some("Avans"), Some(-3))


// Exercise 3
// In this exercise we use Double instead of Int, as it makes more sense with
// these math operators

// i
val sqrt = (x: Either[Double, String]) => x match {
  case Right(err) => Right(err)
  case Left(value) if value < 0 => Right("No sqrt of negative numbers!")
  case Left(value) => Left(scala.math.sqrt(value))
}

// ii
val div10by = (x: Either[Double, String]) => x match {
  case Right(err) => Right(err)
  case Left(0.0) => Right("Division by zero (x == 0)")
  case Left(value) => Left(10.0 / value)
}

// iii
val div1byXminus1 = (x: Either[Double, String]) => x match {
  case Right(err) => Right(err)
  case Left(1.0) => Right("Division by zero (x-1 == 0)")
  case Left(value) => Left(1.0 / (value - 1.0))
}


// Running with correct and faulty input
val composed = div1byXminus1 andThen sqrt andThen div10by

composed(Left(10))
composed(Left(0))
composed(Left(-2))
composed(Left(1))