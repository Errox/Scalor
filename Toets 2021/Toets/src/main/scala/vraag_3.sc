import scala.annotation.tailrec
// ************************************************************************************************
// Vraag 3 van 5 (13 van 50 begripspunten totaal)
//
// Deze vraag bestaat uit 2 delen
// ************************************************************************************************

// ================================================================================================
// Deel a (5 begripspunten)

// Je krijgt een lijst met options van integers. Je wil een lijst met alleen de integers, dus
// alle None's moeten eruit worden gehaald.

// Je krijgt de volgende lijst
val optional: List[Option[Int]] = List(Some(1), None, Some(3), None, Some(5), None, Some(7), None)

// Schrijf een pure functie 'removeNone' die alle None's eruit filtert.
// Deze functie moet recursief zijn en moet pattern matching gebruiken (geen if!).

// De signature van removeNone is:
// removeNone: List[Option[Int]] => List[Int]

// De verwachte return waarde is List(1,3,5,7)
// ================================================================================================

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Antwoord op deel a


def removeNone(l: List[Option[Int]]): List[Int] =
//
  l match {
    case Nil => Nil
    case None :: tail => removeNone(tail)
    case head :: tail =>  head.get :: removeNone(tail)
    case _ => Nil
  }

removeNone(optional)

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Deel b (8 begripspunten)

// Gebruik dezelfde lijst met options op integers als hierboven gegeven. Je wil de som van alle
// integers weten.

// Schrijf een pure functie 'sumSome' die de som berekent van alle niet-None elementen in de lijst.
// Deze functie moet TAIL recursief zijn en moet pattern matching gebruiken (geen if!).
// Vergeet niet dat je @tailrec kan gebruiken.

// De signature van sumSome is:
// sumSome: List[Option[Int]] => Option[Int]
// Let op: de functie moet ook om kunnen gaan met een lege lijst of een lijst met alleen None's.
// Bijvoorbeeld: sumSome(List()) geeft None en sumSome(List(None, None)) geeft None

// De verwachte return waarde van de gegeven lijst is Some(16)
// ================================================================================================

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Antwoord op deel b


def sumSome(l: List[Option[Int]], result: Int = 0): Int =
//
  l match {
    case Nil => result
    case None :: tail => sumSome(tail, result + 0)
    case head :: tail => sumSome(tail, result + head.get)
  }

sumSome(optional)

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++