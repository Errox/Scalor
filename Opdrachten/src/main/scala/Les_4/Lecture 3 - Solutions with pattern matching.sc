// Exercise 1
val people = List("Alfred","Boris","Ann","Jan","Anya","Monique", "Christophe", "Jan", "Joris", "Bert", "Olaf")

// 1.i
// Add "Dear " in front of each name. Result: List[String]
people.map("Dear " + _)
//1.ii
// Create a function (String) => String that add "Dear " in front of the string
// Apply this function to each name and print the result for each name to console on a separate line.
// Answer: Unit. The result is only shown in console.
def dear(name: String) = "Dear " + name
people.map(dear).foreach(println) // Equivalent to people.map(person => dear(person)).foreach( dearPerson => println(dearPerson))
people.foreach((name) => println(dear(name))) // alternative


//1.iii
// Show a list of all letters (including doubles)
// Result: List[Char]
people.flatMap(_.toCharArray) // Equivalent to people.flatMap(person => person.toCharArray)

//1.iv
// Show a list of all unique letters (Case)
people.flatMap(_.toCharArray).foldLeft(Set[Char]())(_+_).toList

//1.v
// Add together the size of all names
// Result: Int
people.map(_.size).reduce(_ + _)

//1.vi
// Create the string "Dear <name1>, <name2>, ..., <nameN>, "
// Use .fold
// Result: String
people.fold("Dear ")( _ + _ + ",")

//1.vii
// How many people have 'an' (case-insensitive) in their name? Answer: Int
people.count(_.toLowerCase.contains("an"))

//1.viii
// How many names are 3 letters long? Result: Int
people.count(_.size == 3)

//1.ix
// Do all names contain a capital letter?
// Use forall
people.forall((name) => name.toLowerCase != name)

//1.x
// Is there any name with the letter y or q in it?
// Use .exists
// Result: Boolean
people.exists((name)=> name.contains('y') || name.contains('q'))

//1.xii
// Show all names with the letter y or q in them
// Result: List[String]
people.filter((name)=> name.contains('y') || name.contains('q'))

//1.xiv
// Can you group the names by their size?
// Result: Map[Int, List[String]]
// Tip: Check .groupBy
people.groupBy(_.size)

//1.xv
// Are there duplicate names in the list?
// Result: Boolean
people.groupBy((name)=> name).exists({case (_,names) => names.size > 1})
people.groupBy((name)=> name).exists((namesTuple) => { //alternative
  val (_, namesList) = namesTuple
  namesList.size > 1
})
// Each exercise has at least two solutions, one in imperative
// style (nameImp) and one in pattern matching style (just name)

// A list to test the functions on
val l = List(1,3,5,4,2)

// Exercise 2
// i
def size(l: List[Int]): Int = l match {
    case Nil => 0
    case _::tail => 1 + size(tail)
  }

def sizeImp(l: List[Int]): Int = {
  if (l == Nil)
    0
  else {
    val _ :: tail = l
    1 + size(tail)
  }
}

size(l)
sizeImp(l)


// ii
def sum(l: List[Int]): Int = l match {
  case Nil => 0
  case head::tail => head + sum(tail)
}

def sumImp(l: List[Int]): Int = {
  if (l == Nil)
    0
  else {
    val head :: tail = l
    head + sum(tail)
  }
}

sum(l)
sumImp(l)


// iii
def binaryMax(x: Int, y: Int): Int = if (x > y) x else y

def myMax(l: List[Int]): Int = l match {
  case head::Nil => head
  case head::tail => binaryMax(head, myMax(tail))
  // This case silences a warning, but is strictly not correct!
  case Nil => 0
}

def myMaxImp(l: List[Int]): Int = {
  val head :: tail = l
  if (tail == Nil)
    head
  else {
    val tailMax = myMaxImp(tail)
    if (head > tailMax)
      head
    else
      tailMax
  }

}

myMax(l)
myMaxImp(l)


// iv
def replicate(s: String, n: Int): String = n match {
  case 1 => s
  case _ => s + replicate(s, n-1)
}

def replicateImp(s: String, n: Int): String = {
  if (n == 1)
    s
  else
    s + replicateImp(s, n-1)
}

replicate("Avans", 3)
replicateImp("Avans", 3)


// Exercise 3
// i
def map(l: List[Int], f: Int => Int): List[Int] = l match {
  case Nil => Nil
  case head::tail => f(head) :: map(tail, f)
}

def mapImp(l: List[Int], f: Int => Int): List[Int] = {
  if (l == Nil)
    Nil
  else {
    val head :: tail = l
    f(head) :: mapImp(tail, f)
  }
}

map(l, _ * 2)
mapImp(l, _ * 2)


// ii
def foreach(l: List[Int], f: Int => Unit) {
  l match {
    case Nil => Nil
    case head::tail => {
      f(head)
      foreach(tail, f)
    }
  }
}

def foreachImp(l: List[Int], f: Int => Unit) {
  if (l == Nil)
    Nil
  else {
    val head :: tail = l
    f(head)
    foreachImp(tail, f)
  }
}

foreach(l, println)
foreach(l, println)


// iii
def reduce(l: List[Int], f: (Int, Int) => Int): Int = l match {
  case head::Nil => head
  case head::tail => f(head, reduce(tail, f))
  // This case silences a warning, but is strictly not correct!
  case Nil => 0
}

def reduceImp(l: List[Int], f: (Int, Int) => Int): Int = {
  val head :: tail = l
  if (tail == Nil)
    head
  else
    f(head, reduceImp(tail, f))
}

reduce(l, _ + _)
reduceImp(l, _ + _)


// iv
def count(l: List[Int], f: Int => Boolean): Int = l match {
  case Nil => 0
  case head::tail if f(head) => 1 + count(tail, f)
  case _::tail => count(tail, f)
}

def countImp(l: List[Int], f: Int => Boolean): Int = {
  if (l == Nil)
    0
  else {
    val head :: tail = l
    if (f(head))
      1 + count(tail, f)
    else
      count(tail, f)
  }
}

count(l, _ > 2)
countImp(l, _ > 2)


// v
def forall(l: List[Int], f: Int => Boolean): Boolean = l match {
  case Nil => true
  case head::tail => f(head) && forall(tail, f)
}

def forallImp(l: List[Int], f: Int => Boolean): Boolean = {
  if (l == Nil)
    true
  else {
    val head :: tail = l
    f(head) && forallImp(tail, f)
  }
}

forall(l, _ > 0)
forallImp(l, _ > 0)


// vi
def exists(l: List[Int], f: Int => Boolean): Boolean = l match {
  case Nil => false
  case head::tail => f(head) || exists(tail, f)
}

def existsImp(l: List[Int], f: Int => Boolean): Boolean = {
  if (l == Nil)
    false
  else {
    val head :: tail = l
    f(head) || existsImp(tail, f)
  }
}

exists(l, _ == 5)
exists(l, _ == 5)


// vii
def filter(l: List[Int], f: Int => Boolean): List[Int] = l match {
  case Nil => Nil
  case head::tail if f(head) => head :: filter(tail, f)
  case _::tail => filter(tail, f)
}

def filterImp(l: List[Int], f: Int => Boolean): List[Int] = {
  if (l == Nil)
    Nil
  else {
    val head :: tail = l
    if (f(head))
      head :: filter(tail, f)
    else
      filter(tail, f)
  }
}

filter(l, _ < 3)
filterImp(l, _ < 3)


// viii
def take(l: List[Int], n: Int): List[Int] = n match {
  case i if i > 0 => l.head :: take(l.tail, i-1)
  case _ => Nil
}

def takeImp(l: List[Int], n: Int): List[Int] = {
  if (n > 0)
    l.head :: take(l.tail, n-1)
  else
    Nil
}

take(l, 3)
takeImp(l, 3)


// ix
def drop(l: List[Int], n: Int): List[Int] = n match {
  case i if i > 0 => drop(l.tail, i-1)
  case _ => l
}

def dropImp(l: List[Int], n: Int): List[Int] = {
  if (n > 0)
    dropImp(l.tail, n-1)
  else
    l
}

drop(l, 3)
dropImp(l, 3)


// x
def contains(l: List[Int], value: Int): Boolean = l match {
  case Nil => false
  case head::tail if head == value => true
  case _::tail => contains(tail, value)
}

def containsImp(l: List[Int], value: Int): Boolean = {
  if (l == Nil)
    false
  else {
    val head :: tail = l
    if (head == value)
      true
    else
      contains(tail, value)
  }
}

contains(l, 2)
containsImp(l, 2)


// xi
// O(n^2) solution
def reverse(l: List[Int]): List[Int] = l match {
  case Nil => Nil
  case head::tail => reverse(tail) :+ head
}

def reverseImp(l: List[Int]): List[Int] = {
  if (l == Nil)
    Nil
  else {
    val head :: tail = l
    reverse(tail) :+ head
  }
}

reverse(l)
reverseImp(l)

// tail recursive solution in O(n)
def reverseTail(l: List[Int], result: List[Int] = Nil): List[Int] = l match {
  case Nil => result
  case head::tail => reverseTail(tail, head::result)
}

def reverseTailImp(l: List[Int], result: List[Int] = Nil): List[Int] = {
  if (l == Nil)
    result
  else {
    val head :: tail = l
    reverseTailImp(tail, head::result)
  }
}

reverseTail(l)
reverseTailImp(l)


// Exercise 4
// i
def swap(l: List[(String, Int)]): List[(Int, String)] = l match {
  case Nil => Nil
  case head::tail => {
    val (s,i) = head
    (i, s) :: swap(tail)
  }
}

def swapImp(l: List[(String, Int)]): List[(Int, String)] = {
  if (l == Nil)
    Nil
  else {
    val head :: tail = l
    val (s, i) = head
    (i, s) :: swapImp(tail)
  }
}

val ll = List(("Alfred", 4), ("Bob", 2))
swap(ll)
swapImp(ll)


// ii
def zip(lhs: List[Int], rhs: List[Int]): List[(Int, Int)] = (lhs, rhs) match {
  case (_, Nil) => Nil
  case (Nil, _) => Nil
  case (lhead::ltail, rhead::rtail) => (lhead, rhead) :: zip(ltail, rtail)
}

def zipImp(lhs: List[Int], rhs: List[Int]): List[(Int, Int)] = {
  if (lhs == Nil || rhs == Nil)
    Nil
  else {
    val lhead :: ltail = lhs
    val rhead :: rtail = rhs
    (lhead, rhead) :: zip(ltail, rtail)
  }
}

val list1 = List(1,2,3)
val list2 = List(4,5,6)
zip(list1, list2)


// Exercise 5
def printList(l: List[String]) {
  l match {
    case Nil => {} // Nothing to do in this case
    case head::tail => {
      printList(tail)
      println(head)
    }
  }
}

def printListImp(l: List[String]) {
  if (l != Nil) {
    val head :: tail = l
    printListImp(tail)
    println(head)
  }
}

def listen(history: List[String] = Nil) {
  scala.io.StdIn.readLine() match {
    case "quit" => printList(history)
    case message => listen(message :: history)
  }
}

def listenImp(history: List[String] = Nil) {
  val input = scala.io.StdIn.readLine()
  if (input == "quit")
    printListImp(history)
  else
    listenImp(input :: history)
}

// Scala worksheets don't support input, so run this inside a main object

//listen()
//listenImp()