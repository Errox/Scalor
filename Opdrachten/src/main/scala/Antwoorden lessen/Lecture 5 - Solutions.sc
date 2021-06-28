// Exercise 1
// i
def add(x: Int): Int => Int = {
  def adder(y: Int): Int = x + y // 'adder' is the closure, closes over 'x'

  adder
}

val add5 = add(5)

add5(4) // 9


// ii
// This is NOT a closure! All variables are just in scope and no function is returned
def applyFunc(x: Int, y: Int, f: (Int, Int) => Int): Int = f(x, y)

val answer = applyFunc(2, 3, (x: Int, y: Int) => x + y)

answer // 5


// iii
def lucky(x: Int): Int => String =
  (y: Int) =>   // Lambda function is the closure
    if (x == y) // closed over 'x'
      "Lucky number!"
    else
      "Sorry, try again!"

val guess = lucky(5)

guess(2) // Sorry, try again!
guess(5) // Lucky number!


// iv
// This is NOT a closure! The string "Dear " is a constant, not a variable, so it cannot be closed over.
val people = List("Alfred","Boris","Ann","Jan","Anya","Monique", "Christophe", "Jan", "Joris", "Bert", "Olaf")
people.map("Dear " + _)


// v
// This is NOT a closure! All variables are just in scope of each recursive call.
def sum(l: List[Int]): Int = l match {
  case Nil => 0
  case head::tail => head + sum(tail)
}


// Exercise 2
def makeLogger(): String => Unit = {
  var count = 0

  (message: String) => {
    count += 1
    println(count + " " + message)
  }
}

val log: String => Unit = makeLogger()

log("First message")
log("Second message")
log("Third message")

// To create just one instance of the logger we can use a function that
// is invoked immediately (right when it's defined).
// This way you can be sure each log entry gets a unique ID!
val uniqueLog: String => Unit = {
  var count = 0

  (message: String) => {
    count += 1
    println(count + " " + message)
  }
}

uniqueLog("First message")
uniqueLog("Second message")
uniqueLog("Third message")


// Exercise 3
def makeCounter(): (() => Int, () => Int) = {
  var count = 0

  // It's better to define lambda functions here, as Scala treats them as real functions
  // Functions defined with 'def' are actually methods
  val increment: () => Int = () => {
    count += 1
    count
  }

  val decrement: () => Int = () => {
    count -= 1
    count
  }

  (increment, decrement)
}

val (incr, decr): (() => Int, () => Int) = makeCounter()

incr()
incr()
decr()
incr()
decr()
decr()


// Exercise 4
def createMemory(): String => String = {
  var memory = ""

  (message: String) => {
    val result = memory
    memory = message
    result
  }
}

val remember: String => String = createMemory()

remember("Hello")
remember("World")
remember("")


// Exercise 5
def range(start: Int, end: Int, step: Int): () => Option[Int] = {
  var idx = start

  () => {
    if (idx < end) {
      val result = idx
      idx += step
      Some(result)
    } else {
      None
    }
  }
}

val myRange = range(10, 20, 3)

myRange()
myRange()
myRange()
myRange()
myRange()
myRange()