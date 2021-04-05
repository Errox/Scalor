package Lectures.Basics

object Expressions extends App {
  val x = 1 + 2 //expression
  println(x)

  println(2 + 3 * 4) //Follows default order of priority

  println(1 == x)
  //== != > >= < <=

  println(!(1 == x))
  //! && ||

  var aVariable = 2
  aVariable += 3 //also works with -= *= /= ... side effects

  //Instructions (DO) vs Expressions (VALUE)

  //IF expressions
  val  aCondition = true
  val  aConditionValue = if(aCondition) 5 else 3 //IF expression
  println(aConditionValue)

  var i = 0
  while( i < 10) {
    println(i)
    i += 1
  }

  //Everything in scala is an expression

  val aWeirdValue = (aVariable = 3) //Unit === void
  println(aWeirdValue) //Void 'returns' ()

  //side effects: println(), whiles, reassigning

  //Code blocks; also expressions
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if(z > 2) "hello" else "goodbye"
  }


}
