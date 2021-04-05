package Lectures.Basics

object DefaultArgs extends App {

  //By giving a default argument we don't have to clutter our function calls with numbers we do not want.
  def trFact(n: Int, acc: Int = 1): Int =
    if(n <= 1) acc
    else trFact(n-1, n*acc)

  val fact10 = trFact(10)
  //We can still use acc if we so desire
  val fact102 = trFact(10, 2)
}
