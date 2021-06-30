import scala.language.postfixOps

val people = List("Alfred","Boris","Ann","Jan","Anya","Monique", "Christophe", "Jan", "Joris", "Bert", "Olaf")

people.map("Dear " + _)

people.map(_.take(1))

def dear(name: String) = "Dear " + name

people.map(dear).foreach(println)

val result = people.map {_ + "s"}

val numbers = List(5, 48 , 189 , 894 , 506 ,9, 89)

object Test {
  var name  = "Noel"

  def hello(other: String): String =
    name + " says hi to " + other

  def echo: String = "Probably the best object ever"

  def cheese: Any => String = "Henlo " + _
}
Test.name
Test.hello("cheese")
Test.name = "Hans"
Test.hello(Test.cheese("cheese"))


object calc {
  def square(x: Double) = x * x
  def cube(x: Double) = x * square(x)
}

calc.cube(4)


object calc2 {
  def square(x: Int) = x * x
  def cube(x: Int) = x * square(x)
}

calc2.cube(4)

object persone {
  val firstName = "Joey"
  val lastName = "Bronkelen"
}

object alien {
  def greet(p: persone.type) =
    "Greetings, " + p.firstName + " " + p.lastName
}

print(alien.greet(persone))

def square(in: Double): Double =
  in * in

// First-class functions
val addition = (x: Int, y:Int) => x + y
val substraction = (x: Int, y: Int) => x - y
val myFunctionMap = Map("addition" -> addition, "substraction" -> substraction)

var retrievedFunction = myFunctionMap("addition")
retrievedFunction(1,2)

retrievedFunction = myFunctionMap("substraction")
retrievedFunction(1,2)

//High-order functions, functions that take functions as parameter
def functionWithFuncParam (number: Int, aFunction: (Int) => Int): Int ={
  aFunction(number)
}

def function2 (str: String, BFunction: (String) => String): String ={
  BFunction(str + "bingous")
}
def add2 (number: Int) : Int = number + 2

def funcReturningFunc(): (Int) => Int ={
  add2
}

//Signature: (input) => output
// (Int) => Int
// A function that takes an Int as input and outputs an Int

// ((Int) => Int) => Int
// A function that takes an (Int) => Int function as input and returns an Int

(x: Int) => 2 * x
val doubleIt = (x: Int) => 2* x

functionWithFuncParam(4, doubleIt)
functionWithFuncParam(4, (x: Int) => 2 * x)
functionWithFuncParam(4, _ * 2)

val yeet = (x: String) => x + " bongus "
function2("yeet ", yeet)


val greet = (x: String) => "hello " + x + " 2 "
val duplicate = (x: String) => x * 2

val composed = greet compose duplicate // eerst rechts, dan links aan geroepen
val chained = greet andThen duplicate  // eerst links dan rechts.

composed("bob")
// Hello -> duplicate (bob * 2 = bobbob) -> 2
chained("bob")
// Hello -> greet (x = bob -> 2) -> Hello -> x = bob -> 2

val composed00 = greet.compose(duplicate)
val chained00 = greet.andThen(duplicate)

class Person(first: String, last: String, title: String) {
  var first2: String = first
  var last2: String = last
  var title2: String = title
}

def greet(person: Person, strategy: Person => String): String =
  "hello " + strategy(person)

def formal (person: Person): String =
  person.title2 + " " + person.last2

def informal(person: Person): String =
  person.first2

def all (person: Person): String =
  person.title2 + " " + person.first2 + " " + person.last2


val john = new Person("John", "De Hond", "Mega Faggot")

greet(john, formal)
greet(john, all)
greet(john, informal)

val nums = Array(1,2,3,4)

nums.map(_+1) // Loopt door het hele zooitje heen en + 1
// In plaats een for loop, loop je er doorheen met map,
// en gooi je de manipulatie er doorheen

nums.reduce(_+_) // nums.sum
// Met reduce gaat het dus van 1 + 2 -> 3. 3 + 3 -> 6. 6 + 4 -> 10
nums.reduce(doubleIt(_)+_)
//Hierbij krijg je dus de optel van alles binnen de array.

nums.reduce((x, y) => x max y)
//Alles binnen een array dat geloopt wordt, en dus uiteindelijk 4 krijgt

nums.foreach(println(_))


//Fold Applies a binary operation to all elements in an arbitrary order
//Fold(init)(f)
val words = Array("Higher","order","functions")
val concatWithSpace = (x:String, y: String) => x + " " + y

words.reduce(concatWithSpace)
words.fold("Topic: ")(concatWithSpace)

//Map gaat vanuit een collectie naar een collectie
//Reduce gaat vanuit een collectie naar een element

//Reduce kan dus ook gebruikt worden met ReduceRight en ReduceLeft (ook voor fold)
//Dit is dus om vanuit de array vanuit links naar rechts gaat, of van rechts naar links


//Count returns the count of elements for which f evaluates to true
val numbers = -2 to 7

numbers.count(_>0)
numbers.count(_<0)
//DIt lijkt op een reduce, vanuit een collection gaat deze naar een int

//Forall returns if f evaluates to true for all elements, else false
val words = Array("Hello", "there")
val words2 = Array("Hello", "there", "all")
val words4 = Array("Hello", "there", "all", "theyre", "cheese")


//De array krijgt dan een bepaalde query mee, dus voor alles moet de size dan gelijk zijn aan 5
words.forall(_.size == 5)
// Bij words2 is dit dus niet het geval en dus ook false.
words2.forall(_.size == 5)

//Een exist(f): returns true if f evaluates to true for at least 1 element, else false
words.exists(_.size == 3)
words2.exists(_.size == 3)

//filter returns all elements for which f evaluates to true
words.filter(_.contains("l"))
words2.filter(_.contains("l"))

//Take and drop take Nth (this case 1th) and drop removes the 1th
words2.take(1)
words2.drop(1)

//The right/left drop/take does the same but then from the given side of the array.
words2.takeRight(1)
words2.dropRight(1)

//Slice returns all elements starting from position from to position to
words4.slice(2, 4)

//zip returns pairs of element from this collection and the parameter collections
words4.zip(words)

//Contains returns true of the collection contains the element, else false
words4.contains("Hello")
words4.contains("yeet")

//Reverse returns the reverse of the ordered collection
words4.reverse

def add(x:Int): Int => Int ={
  return (y: Int) => x + y
}
def addv(x:Int) = (y: Int) => x + y

def addAlternative = (x: Int) => (y: Int) => x * y


add(3)(4)
val addTwo = add(2)

addTwo(1)
addTwo(2)

val amount = List(2, 3)
val price = List(3.0, 2.5)

amount zip price

val full = (a: Int, p: Double) => a * p

val disc = (a: Int, p: Double) => (a * p) * 0.2

val happyHour = (a: Int, p: Double) => (a + 1) /2 * a * p

def calcPrice(
               amount: List[Int],
               price: List[Double],
               strategy: (Int, Double) => Double) = {
//  amount zip price map(t => {
//    val (a, p) = t
//    strategy(a, p)
//  }) sum

  amount.zip(price).foldLeft(0.0)((s, t) => s + strategy.tupled(t))
}

calcPrice(amount, price, full)
calcPrice(amount, price, disc)
calcPrice(amount, price, happyHour)
