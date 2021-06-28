// Exercise 1
def concat(name: String, age: String, profession: String): String =
  name + " is " + age + " years old and is a " + profession

def concatCurried(name: String)(age: String)(profession: String): String =
  name + " is " + age + " years old and is a " + profession

concatCurried("Alfred")("21")("programmer")


// Exercise 2
val profession = concatCurried("Alfred")("21")(_)

profession("programmer")


// Exercise 3
val name = concatCurried(_: String)("21")("programmer")

name("Alfred")


// Exercise 4
def lucky(luckyNumber: Int)(nextNumber: () => Int): Unit = {
  val guess = nextNumber()

  if (guess == luckyNumber)
    println("You won!")
  else {
    println("Wrong guess...")
    lucky(luckyNumber)(nextNumber)
  }
}

var count = 0

def counter(): Int = {
  count += 1
  count
}

lucky(5)(counter)