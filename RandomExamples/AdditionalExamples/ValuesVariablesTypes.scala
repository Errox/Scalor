package Lectures.Basics

object ValuesVariablesTypes extends App {
   //Values are not immutable;
  // Values do not need to be defined, compiler can infer types
  val x = 42
  println(x)

  val aString: String = "Hi"

  val aBoolean: Boolean = true
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 1234123141
  val aFloat: Float = 2.0f //Java syntax
  val aDouble: Double = 3.14

  //variables
  //variables are ofcourse immutable
  var aVariable: Int = 4

  aVariable = 5 //side effects

  //Prefer vals over vars
}
