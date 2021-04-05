package Lectures.Basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial (n: Int): Int = {
    if(n <= 1) 1
    else {
      println("Computing factorial of " + n + " First need factorial of " + (n-1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }
  }
  factorial(10)
  //stackoverflow at 5000

  //Using BigInt so we can show bigger numbers (e. g factorial of 5000)
  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if(x <= 1) accumulator
      else factHelper(x -1, x * accumulator) // Tail recursion, use recursive call as the last expression

    factHelper(n, 1)
  }

  println(anotherFactorial(5000))
  //WHen using loops use tail recursion

  // anotherFactorial(10) = factHelper(10, 1)
  // = factHelper(9, 10 * 1)
  // = factHelper(8, 9 * 10 * 1)
  // = factHelper(7, 8 * 9 * 10 * 1)
  // etc.
  // = factHelper (2, 3* 4 * ... * 10 * 1)
  // = factHelper (1, 2 * 3 * 4 ... * 10 * 1)
  // return 1 * 2 * 3 * 4 ... * 10

  /*
    1. Concatenate a string n times
    2. IsPrime function tail recurisve
    3. Fibonacci function, tail recursive
   */
}
