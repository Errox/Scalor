package Lectures.Basics

object Functions extends App {
  //A normal function does not need their return type defined, it is best practice though.
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("Order", 66))

  def aParameterlessFunction(): Int = 42
  //println(aParameterlessFunction())
  //println(aParameterlessFunction)

  //Recursive functions need their return type defined
  def aRepeatedFunction(aString: String, n: Int): String = {
    if(n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("Hello", 3))
  //When you need loops, use recursion

  def aFunctionWorksWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n-1)
  }

  //1. Greet a person (name, age) => Hi my name is $name, and I am $age years old
  def greetAPerson(name: String, age: Int): String ={
    "Hi my name is: " + name + " and I am " + age + " years old."
  }
  greetAPerson("Barry", 69)
  //2. Factorial function 1 * 2 * 3 * ... * n (recursive)
  def factorial(n: Int): Int =
    if(n <= 0) 1
    else n * factorial(n-1)

    println(factorial(5)) //120

  //3. Fibonacci function f(1) = 1 f(2) = 1 (fn) = f(n - 1) + f(n - 2)
  def fibonacci(n: Int): Int =
    if (n <= 2) 1
    else fibonacci(n -1) + fibonacci(n - 2)

  println(fibonacci(8))

  //4. Test if a number is prime
  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if(t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(n / 2)
  }
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))
}
