//Closures
def createClosure(): () => String = {
  val s = "hello there" // Omdat de val S nergens anders behalve in de scoop van createClosure kan.
  // wordt die uiteindelijk wel gebruikt buiten de scope omdat we de def closure uiteindelijk
  // nog later kunnen gebruiken doormiddel van c() uit te voeren

  def closure () : String ={
    s
  }
  closure
}

val c = createClosure()

c() // dit betekend dus dat de compiler nu de scoop laat lekken dat het buiten de scope / functie te gebruiken is.



//----

//Dynamic scope
//Het is mogelijk om een dynamische scope te gebruiken
var x = 0
def printer (): Unit ={

  println(x)
}

def work() : Unit = {
  x = 10

  print()

  x = 20

  printer()
}
// een dynamic scope is een alternatief op de lexical scope
// Wanneer je functies aanroept alle variabels die gemaakt zijn zullen blijven bestaan
// Bovenste stukje werkt niet in scala (wtf)
// Het keyword this wordt gebruikt in javascript en refereert dus naar het parent object.
// Dit maakt dus deeluit van een dynamic scope

// Pure functie mag geen mutables hebben, Geen side effecten gedurende de functie.
// en het mag niet andere resultaten geven voor dezelfde input die we geven

//Voorbeeld  pure functie
def makeGreeter(greeting: String) : String => String =
  (name : String) => greeting + name

val greetingWithHello = makeGreeter("Hello ")
val greetingWithHi = makeGreeter("Hi ")

greetingWithHello("bob")
greetingWithHi("Bob")

// Dit is een pure functie. Er wordt gebruik gemaakt van de geleverde variabelen

// Je hebt te maken met closure binnen de functies.
// De hello zit zelf verstopt in de greetingsWithHello en dus ook nogsteeds een closure


//Closures hebben ook temaken met => (fat arrow), deze zijn altijd closures.

def replicate(str: Option[String], n: Option[Int]): Option[String] =
  (str, n) match { //van de twee parameters een tuple maken om tergelijkertijd te matchen
    case (None, _) => None
    case (_, None) => None // Kijken of we iets missen uberhaupt in de tuplecase
    case (_, Some(n)) if n <= 0 => Some("") // een guard om te checken of er wel een
    // value in zit die aan de guard zijn terms voldoet.
    case (Some(s), Some(n)) => Some(s+replicate(Some(s), Some(n-1)).get)

  }
replicate(Some("Avans"), Some(4))

def sqrt(value:Either[Double, String]): Either[Double, String] =
  value match{
    case Left(number) if number >= 0 => Left(scala.math.sqrt(number))
    case Left(number) => Right("Error: sqrt of negative number")
    case Right(_) => value //Dit kan ook met err ipv err maar dit is een extra var

  }

sqrt(Left(4.0))
sqrt(Left(-4.0))
sqrt(Right("Random Error"))

def divByTen(value: Either[Double, String])=
  value match{
    case Left(0.0) => Right("error: division by zero")
    case Left(number) => Left(10.0 / number)
    case Right(_) => value
  }

// Dit is geen probleem als je hier hebt
// val divOneMinusOne = () => {}
def divoneByMinusOne(value: Either[Double, String])=
  value match{
    case Left(1.0) => Right("error: division by zero (1 - 1 == 0)")
    case Left(number) => Left(1.0 / (number - 1.0))
    case Right(_) => value
  }

divoneByMinusOne(Left(20))
divoneByMinusOne(Left(1))

val composed = divByTen _ andThen sqrt _ andThen divoneByMinusOne _
// De _ moeten er onder om dan het mee gegeven functie een variabel te maken
// Dit moet omdat het functies zijn die we gebruiken en geen lambda's

composed(Left(2.5))
// 10 gedeeld door 2.5 is 4 wortel van 4 is 2 en min 1 is 1
composed(Left(10))
// Laatste is natuurlijk dan de wortel van 1 want 10 / 10 is 1. En er is geen wortel van 1
composed(Left(-10))
// dit is de square root door een negatief nummer, wat niet gaat. Fouten worden dan doorgegeven door de functies daarboven
//composed(Left(0))
// En dit is delen door 0 wat helemaal niet gaat.



//Currying with ecorator pattern
def log[A](value: A): A ={
  println("logging: "+ value)
  value
}

def isEven(value: Int): Boolean =
  value % 2 == 0


def isEvenWithLogging(value: Int): Boolean =
  (log[Int] _ andThen isEven _ andThen log[Boolean] _) (value)

isEvenWithLogging((3))


// Een functie toe kunnen voegen en dat het resultaat van een andere nodig heeft
def addCurry(x: Int): Int => Int = (y: Int) => x + y

addCurry(1)   // function Int => Int that adds 1 to its input
addCurry(1)(2) // 3 (note that we do two function calls here)

// Hierbij gebruik je add curry, maar als je maar 1 parameter meegeeft,
// (dus maar 1 functie gebruikt) dat je dan een functie / object klaar hebt staan om te gebruiken
// Maar als vervolgens met 2 aangeroepen wordt, krijg je een resultaat.



// uitgebreid currying

//Uncurried function
def add(x: Int, y: Int): Int = x + y

//Curried with lambda
def addCurryLambda(x: Int): Int => Int = (y: Int) => x + y

val addCurryLambda5: Int => Int = addCurryLambda(5)

// curried by scala (syntactical sugar)
def addCurryScala(x:Int)(y:Int): int = x + y

val addCurryScala5: Int => Int = addCurryScala(5)

// using the functions
add(5,2)
addCurryLambda(5)(2)
addCurryLambda5(2)
addCurryScala(5)(2)
addCurryScala5(2)