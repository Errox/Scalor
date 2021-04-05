// Exercise 1
// Swap a tuple with tuple deconstruction
def swap(t: (Int, String)): (String, Int) = {
  val (left, right) = t
  (right,left)
}

swap((25, "Sugar"))

// Exercise 2
// Double the first element in a list using list deconstruction
def doubleHead(list: List[Int]): List[Int] = {
  list.head * 2 :: list.tail
}

doubleHead(List(5,8,3))

// Exercise 3
// Return a function that adds x and y
def add(x: Int): Int => Int = {
  def adder(y: Int): Int = x + y

  adder
}

val add5 = add(5)(4)
//add5(4)

// Exercise 4
// Return a function that multiplies x and y
def multiplyBy(x: Int): Int => Int = {
  def mult(y: Int): Int = x * y

  mult
}

val mul5 = multiplyBy(5)

mul5(4) // 20


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
def sizeDescription(list: List[Int]): String = {
  if (list.size < 10)
    "Short"
   else if (list.size < 100)
    "Long"
   else
    "Very long"
}

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

zip(List(1,2,3), List(4,5,6))


// A more functional approach is to use folds
def totalFunctional(amount: List[Int], price: List[Double], strategy: (Int, Double) => Double): Double = {
//  val perDrink = amount zip price
//  perDrink.foldLeft[Double](0)((acc, kind) => {
//    val (amount, price) = kind
//    strategy(amount, price) + acc
//  })
  amount.zip(price).foldLeft(0.0)((s,t) => s + strategy.tupled(t))
}


val fullPrice = (amount: Int, price: Double) => amount * price

val discount10 = (amount: Int, price: Double) => 0.9 * fullPrice(amount, price)

// Note that due to rounding amount/2 is NOT the same as amount - amount/2
val happyHour = (amount: Int, price: Double) => (amount - amount/2) * price

totalFunctional(List(2,3), List(3.0,2.5), fullPrice)
totalFunctional(List(2,3), List(3.0,2.5), discount10)
totalFunctional(List(2,3), List(3.0,2.5), happyHour)