// ************************************************************************************************
// Question 5 of 7 (9 points total)
//
// This question has 3 parts
// ************************************************************************************************


// ================================================================================================
// Part 1 (3 points)

// Here is a list of lists of integers.
val square = List(List(1,2,3,4), List(5,6,7,8), List(9,10,11,12), List(13,14,15,16))


// Write a pure recursive function to get the first element of each list. Use pattern matching
// where applicable.

// So the function should return List(1,5,9,13).
// ================================================================================================

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 1
def getFirst(l: List[List[Int]]): List[Int] = {
  l match {
    case Nil => Nil
    case head :: tail => head.head :: getFirst(tail)
  }
}

getFirst(square)

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



// ================================================================================================
// Part 2 (3 points)

// You're given a list that contains options of integers. You want
// to only keep the entries that actually contain an integer.
// You're given the following list:
val optional: List[Option[Int]] = List(Some(1), None, Some(3), None, Some(5), None, Some(7), None)

// Write a pure function 'removeNone' that filters out all None elements.
// This function should be recursive and use pattern matching where applicable.

// The signature of the removeNone function is:
// removeNone: List[Option[Int]] => List[Int]

// The expected result is List(1,3,5,7)
// ================================================================================================

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 2
def removeNone(l: List[Option[Int]]): List[Int] = {
  l match {
    case Nil => Nil
    case None :: tail => removeNone(tail)
    case Some(i) :: tail => i :: removeNone(tail)
  }
}


removeNone(optional)

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



// ================================================================================================
// Part 3 (3 points)

// You're given a list containing both integers and strings. You want
// to use the values as separate lists, based on their type.

// You're given the following list:
val mixed: List[Either[Int, String]] = List(Left(1), Right("two"), Left(3), Right("four"), Left(5), Right("six"), Left(7), Right("eight"))

// Write a pure function 'split' that separates the mixed list into two lists each containing just one
// type.
// This function should be recursive and use pattern matching where applicable. You get full
// points when there are no nested match statements.

// The signature of this split function is:
// split: List[Either[Int, String]] => (List[Int], List[String])

// The expected result is (List(1,3,5,7), List("two", "four", "six", "eight"))
// ================================================================================================

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 3
def split(l: List[Either[Int, String]]): (List[Int], List[String]) = {
  l match {
    case Nil => (Nil, Nil)
    case Left(i) :: tail => {
      val (l, r) = split(tail)
      (i :: l, r)
    }
    case Right(s) :: tail => {
      val (l, r) = split(tail)
      (l, s :: r)
    }
  }
}


split(mixed)

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
