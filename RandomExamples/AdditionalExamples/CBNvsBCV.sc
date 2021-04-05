def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  def calledByname(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

/*
 * Using nanotime to see differences between calls
 * Calling a function by value is the same as any OO language
 */
  calledByValue(System.nanoTime())
/*
 * Calling a function by name is different however
 * The value is given as-is, hence why it's a callbyname function
 * Expression is evaluated every time. So by using two println's
 * The value x is updated twice.
 * Useful in things that might fail or lazy streams
 */
  calledByname(System.nanoTime())

//Example
def infinite(): Int = 1 + infinite()
def printFirst(x: Int, y: => Int) = println(x)

//printFirst(infinite(), 42) This would give a stack overflow error
/*
 * This function however works, because of call by name
 * ByName paramater (=>) delays the evaluation of the expression till it's used
 * Hence why we don't crash, since it isn't used in printFirst
 */
printFirst(42, infinite())