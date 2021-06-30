import scala.annotation.tailrec
import scala.reflect.internal.ClassfileConstants.swap

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


//recursive function Overflow at 1000000
def teachersNeededFor(numberOfStudents: Int): Int ={
  if (numberOfStudents <= 30)
    1
  else
    1 + teachersNeededFor(numberOfStudents - 30)
}

teachersNeededFor(30)
teachersNeededFor(45)
teachersNeededFor(231312)

// recursive function that removes the last object
// but keeps the result to be given into the next itteration
@tailrec
def teachersNeededFor2(numberOfStudentss: Int, result:BigInt = 0): BigInt ={
  if (numberOfStudentss <= 30)
    result + 1
  else
    teachersNeededFor2(numberOfStudentss - 30, result + 1)
}

teachersNeededFor2(30)
teachersNeededFor2(45)
teachersNeededFor2( 1001000000)

teachersNeededFor(233)

val optional: List[Option[Int]] = List(Some(1), None, Some(3), None, Some(5), None, Some(7), None)
// Recusive list fiteren op null formats
def removeNone(l: List[Option[Int]]): List[Int] =
//
  l match {
    case Nil => Nil
    case None :: tail => removeNone(tail)
    case head :: tail =>  head.get :: removeNone(tail)
    case _ => Nil
  }

removeNone(optional)


//Pattern matching (soort van switch)
def matchTest(x: Int) : String =
  x match {
    case 1 => "one" //Er wordt alitjd van boven naar benede gekeken.
    case 2 => "two"
    case _ => "many" // Dus alles wat niet in de case zit, eindigd dan hier
  }

matchTest(2)

//object Animal extends Enumeration {
//  type Animal = Value
//  val Dog, Cat, Cow = Value
//}
//
//def makeSound(animal: Animal): String ={
//  animal match{
//    case Animal.Dog => println "Woof!"
//    case Animal.Cat => println "Miauw"
//    case Animal.Cow => println "mooo"
//  }
//}

//makeSound(Animal.Cow)

// Het kan ook gebruikt worden voor type checking
def checkType(value: Any) ={
  value match {
    case x: Int => x + " is an int"
    case x: String => x + " is an string"
    case x => x + " is something else"
  }
}
checkType(5)
checkType("yeet")
checkType(List(2,2,2,21))


//Decomposition
def addTwoToEachElement(list: List[Int]): List[Int] = {
  list match {
    case Nil => Nil //wanneer de lijst leeg is, geef ik een lege lijst terug
    case head::tail => (head + 2) :: addTwoToEachElement(tail) // Doormiddel van de head::tail, kan ik 'm al deconstructen
    // Daarna kan je dus de head aanpassen, en de rest doorgeven aan de tail.
  }
}

//Oude code
// De stukken doen precies hetzelfde als hierboven, maar een stuk korter dan die van benede.
def addTwoToEachElement(list: List[Int]): List[Int] ={
  if(list == Nil)
    Nil
    else{
      val head::tail = list
      val newHead = head + 2
      newHead :: addTwoToEachElement(tail)
    }
}


//Guards binnen pattern matchin.
// Doormiddel van guards moet plaats je extra restricties binnen je pattern matching
def canStudentsFitInClassroom (number: Int) : String ={
  number match {
    case num if num < 5 => "Very easily" // Hierbij zijn alle nummers onder de 5 dus toegankelijk voor de case
    case num if num < 25 => "easily" // Hierbij zijn alle nummers onder de 25 dus toegankelijk voor de case
    case num if num < 30 => "Only if some sit on the floor" // Hierbij zijn alle nummers onder de 30 dus toegankelijk voor de case
    case _ => "no way"
  }
}

// Guard 2
def teachersNeededFor(NumberOfstudents: Int): Int = {
  NumberOfstudents match{
    case num if num <= 30 => 1 // als dus de numberofstudents gelijk 30 is. Als die dus kleiner is dan 30 dan krijg je 1.
    case _ => 1 + teachersNeededFor(NumberOfstudents - 30) // zo niet zal deze getriggerd wordne
  }
}

// Functie om te vertellen of de list op volgorde zit, dus 1 -> 2 -> 4
def isAscending(list: List[Int]) : Boolean = {
  list match{
    case Nil => true //1
    case _::Nil => true //2
    case el1::el2::_ if el1 >= el2 => false //3
    case _::tail=> isAscending(tail) //4
  }
}

//Dit is de functional versie van
def isAscending1(list: List[Int]): Boolean = {
  if (list == Nil) //1 Check of de list 0 is of niet
    true
    else{
      val element1::tail = list
      if(tail == Nil)
        true //2 List of length is 1 dus betekend dat de array op is en niet verder kan.
      else {
        val element2 = tail.head
        if (element1 >= element2)
          false //3 List of length is dus groter dan het vorige item.
        else
          isAscending1(tail) //4 List is nog niet voorbij, maar de tail is niet groter dan de vorige
      }
  }
}

val list = List(2, 4, 5 ,6)
val listw = List(2, 1, 23, 41)

isAscending(list)
isAscending1(list)
isAscending(listw)
isAscending1(listw)

//An Option can contain an object or nothing
val someInt: Option[Int] = Some(4)
val noInt: Option[Int] = None

//An either can contain one of two values (either left or right)
val left: Either[Int, String] = Left(4)
val right: Either[Int, String] = Right("Hello")

def getLastElement2(l: List[Int]): Option[Int] = {
  l match {
    case head :: Nil => Some(head) // Als m'n head nil is, heb je het laatste element
                                    // Omdat je een option moet terug geven, geef je 'm dus terug als Some
    case _ :: tail => getLastElement2(tail)  // Als dit nog niet de laatste is, dan gaan we recursive er op terug.
    case Nil => None // Als die leeg is, geven we none terug
  }
}
//Nil Is een list[Int]
None
Nil

getLastElement2(List(1,2))  // Some(2)
getLastElement2(List())     // None
// Options kan goed gebruikt worden om zo exceptions op te brengen
// en uit te lezen als het dieper in het programma fout gaat

//Instead of using exceptions you can use Either to pass the error
// through functions until you can handle them
val divideOneWith2 = (denominator: Either[Double, String]) =>
  denominator match{
    case Left(0.0) => Right("Error: divide by zero") // Als Left dus fout gaat, moet die door naar rechts
    case Left(value) => Left(1.0 / value) // ALs dit niet het geval is, gaan we door met happy flow
    case Right(err) => Right(err)
  }
val addOne = (num: Either [Double, String]) =>
  num match {
    case Left(value) => Left(value + 1.0)
    case Right(err) => Right(err) // als right geset is, is er iets fout gegaan. Dat moet je als eerst afhandelen.
  }

(divideOneWith2 andThen addOne) (Left(0.0))
// Doormiddel van Either ontloop je dus niet de functions zoals bij een Exception.
// De stack wordt volledig geskipt als een diepere functie crasht.
// Either gooit deze terug naar de eerste functie en kan elk programma deze als doorgeefluik terug halen



def size(list: List[Int]): Int ={
  list match {
    case Nil => 0
    case _::tail => 1 + size(tail)
  }
}
size(List(2,3,4,1))
// Size wordt zo geteld hoeveel items er binnen een list zitten

def drop[A](list: List[A], amount: Int): List[A] ={
  //Drop 0 geeft lijst terug
  (list, amount) match {
    case(_, 0) => list
    case(Nil, _) => Nil
    case (_::tail, n) => drop(tail, n-1)
  }
}
drop(List(1,2,3,4,5), 4)

// Drop de hoeveelheid die mee gegeven wordt

def swap[A,B](list: List[(A,B)]): List[(B,A)] =
  list match {
    case Nil => Nil
    case (a,b)::tail => (b, a) :: swap(tail)
  }

swap(List((1, "one"), (2, "two"), (3, "three")))
swap(List((4,5), (5,4), (2,2)))


@tailrec
def swapTail[A, B](list: List[(A,B)], result: List[(B,A)] = Nil) : List[(B,A)] =
  list match{
    case Nil => result
      case(a,b)::tail=>swapTail(tail, (b,a)::result )
  }


swapTail(List((1, "one"), (2, "two"), (3, "three")))


def factory(logic: Double => Either[Double, String]):
Either[Double, String] => Either[Double, String] =
  (in: Either[Double, String]) => in match{
    case Right(err) => Right(err)
    case Left(value) => logic(value)
  }

val addOne = factory(value => Left(value + 1.0))

val divideOneWith = factory((value: Double) => value match {
  case 0.0 => Right("Error: devide by zero")
  case _ => Left(1.0 / value)
})

(divideOneWith andThen addOne)(Left(0.0))


def zip[A, B](lhs: List[A], rhs: List[B]) : List[(A,B)] ={
  if(lhs == Nil || rhs == Nil)
    Nil
  else{
    val (lhead::ltail, rhead::rtail) = (lhs,rhs)
    (lhead, rhead) :: zip(ltail, rtail)
  }
}

def showNamesWithYOrQ(names: List[String]) : List[String] =
  for (name <- names if name.toLowerCase.contains('y') || name.toLowerCase.contains('q')) yield name

showNamesWithYOrQ(List("Ruud", "Dion", "Ryan", "Quincy", "Youssef"))
