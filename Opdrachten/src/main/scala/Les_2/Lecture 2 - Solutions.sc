// Exercise 1
// Swap a tuple with tuple deconstruction
def swap(t: (Int, String)): (String, Int) = {
  // Using tuple deconstruction here
  val (left, right) = t

  // Return a tuple with the values swapped around
  (right, left)
}

swap((25, "Sugar")) // (Sugar, 25)


// Exercise 2
// Double the first element in a list using list deconstruction
def doubleHead(l: List[Int]): List[Int] = {
  // Using list deconstruction here
  val head :: tail = l

  // Return a newly constructed list with the head doubled
  head * 2 :: tail
}


// Exercise 3
// Return a function that adds x and y
def add(x: Int): Int => Int = {
  def adder(y: Int): Int = x + y

  adder
}

val add5 = add(5)

add5(4) // 9

// Lambda alternative:
def addLambda(x: Int): Int => Int = (y: Int) => x + y

val add5lambda = addLambda(5)

add5lambda(4) // 9


// Exercise 4
// Return a function that multiplies x and y
def multiplyBy(x: Int): Int => Int = {
  def mult(y: Int): Int = x * y

  mult
}

val mul5 = multiplyBy(5)

mul5(4) // 20

// Lambda alternative
def multiplyByLambda(x: Int): Int => Int = (y: Int) => x * y

val mul5lambda = multiplyByLambda(5)

mul5lambda(4) // 20


// Exercise 5
// Return a function that applies a function to x and y
def applyFunc(x: Int, y: Int, f: (Int, Int) => Int): Int = f(x, y)

val answer = applyFunc(2, 3, (x: Int, y: Int) => x + y)

answer // 5


// Exercise 6
// Write a size description of a list that returns
// "short" if less than 10 elements
// "long" if less than 100 elements
// "very long" otherwise
def sizeDescription(l: List[Int]): String =
  if (l.size < 10)
    "short"
  else if (l.size < 100)
    "long"
  else
    "very long"

sizeDescription(List.fill[Int](9)(0)) // short
sizeDescription(List.fill[Int](19)(0)) // long
sizeDescription(List.fill[Int](119)(0)) // very long


// Exercise 7
// Write a function lucky that returns:
// "Lucky number!" if guessed the number
// "Sorry, try again!" otherwise
def lucky(x: Int): Int => String =
  (y: Int) =>
    if (x == y)
      "Lucky number!"
    else
      "Sorry, try again!"

val guess = lucky(5)

guess(2) // Sorry, try again!
guess(5) // Lucky number!


// Exercise 8
// Write a function that pairs two lists into a list of tuples
def zip(left: List[Int], right: List[Int]): List[(Int, Int)] ={
  var result: List[(Int, Int)] = Nil

  for (i <- (left.size min right.size)-1 to 0 by -1) {
    result = (left(i), right(i)) :: result
  }

  result
}

zip(List(1,2,3), List(4,5,6)) // List((1,4), (2,5), (3,6))

// This was not the topic of the lecture, but there is a nice
// recursive alternative
def zipRecursive(left: List[Int], right: List[Int]): List[(Int, Int)] =
  if (left.size == 0 || right.size == 0)
    Nil
  else {
    val lhead :: ltail = left
    val rhead :: rtail = right
    (lhead, rhead) :: zipRecursive(ltail, rtail)
  }

zipRecursive(List(1,2,3), List(4,5,6)) // List((1,4), (2,5), (3,6))


// Exercise 9
// Discount strategy
// This is a mixed approach, functional and imperative
def total(amount: List[Int], price: List[Double], strategy: (Int, Double) => Double): Double = {
  var sum: Double = 0
  val perDrink = amount zip price

  perDrink.foreach(kind => {
    val (amount, price) = kind
    sum += strategy(amount, price)
  } )

  sum
}

// A more functional approach is to use folds
def totalFunctional(amount: List[Int], price: List[Double], strategy: (Int, Double) => Double): Double = {
  val perDrink = amount zip price
  perDrink.foldLeft[Double](0)((acc, kind) => {
    val (amount, price) = kind
    strategy(amount, price) + acc
  })
}

def fullPrice(amount: Int, price: Double): Double = amount * price

def discount10(amount: Int, price: Double): Double = 0.9 * fullPrice(amount, price)

// Note that due to rounding amount/2 is NOT the same as amount - amount/2
def happyHour(amount: Int, price: Double): Double = (amount - amount/2) * price
//def happyHour(amount: Int, price: Double): Double = (amount + 1) / 2 * price

total(List(2,3), List(3.0, 2.5), fullPrice) // 13.5
total(List(2,3), List(3.0, 2.5), discount10) // 12.15
total(List(2,3), List(3.0, 2.5), happyHour) // 8.0

totalFunctional(List(2,3), List(3.0, 2.5), fullPrice) // 13.5
totalFunctional(List(2,3), List(3.0, 2.5), discount10) // 12.15
totalFunctional(List(2,3), List(3.0, 2.5), happyHour) // 8.0
