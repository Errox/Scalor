
/*
  Warming Up (Basics)
 */

// Exercise 1
// Even 3 is an object
3

// Exercise 2
import scala.math._
 sqrt(3)*sqrt(3) // 2.9999999999999996

// Exercise 3
// res variables are val.
// You will receive an error if you try to assign a new value.

// Exercise 4
"Avans" * 3 // AvansAvansAvans
// https://www.scala-lang.org/api/2.12.3/scala/collection/immutable/StringOps.html

// Exercise 5
10 max 2
10.max(2)
// Max is defined in the Int class
// https://www.scala-lang.org/api/2.12.3/scala/Int.html#max(that:Int):Int


/*
  Warming Up (Functions)
 */

// Exercise 1
//Write a function succ(x: Int) that returns the next number. So succ(8) = 9
def succ(x: Int): Int = x + 1
// Lamda alternative: val succ = (x:Int) => x + 1
succ(8) //9

// Exercise 2
//Write function max(x: Int ,y: Int) that returns x or y, whichever is highest.
def max(x: Int, y: Int): Int = x max y
// Lamda alternative: val max = (x: Int, y: Int) => x max y
max(7, 2)

// Exercise 3
//  Write a function doubleMe(x: Int) that returns x + x
def doubleMe(x: Int): Int = x + x
// Lamda alternative: val doubleMe = (x: Int) => x + x
doubleMe(2)

// Exercise 4
//Write a function doubleUs(x: Int, y: Int) that returns doubleMe(x) + doubleMe(y)
def doubleUs(x: Int, y: Int): Int  = {
  doubleMe(x) + doubleMe(y)
}
// Lamda alternative: val doubleUs = (x:Int, y:Int) => doubleMe(x) + doubleMe(y)

// Exercise 5
//Write a function doubleSmallNumber(x: Int) that 
//Returns x if x > 100
//Else returns doubleMe(x)
def doubleSmallNumber(x: Int): Int = {
  if(x > 100)
    x
  else
    doubleMe(x)
}

// Lamda alternative:
//val doubleS = (x: Int) =>
//  if(x > 100)
//    x
//  else
//    doubleMe(x)

doubleSmallNumber(14)