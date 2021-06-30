// ************************************************************************************************
// Vraag 4 van 5 (10 van 50 begripspunten totaal)
//
// Deze vraag bestaat uit twee delen
// ************************************************************************************************


// ================================================================================================
// Deel a (5 begripspunten)
// ================================================================================================
// De volgende vier functies die een string aanpassen zijn gegeven.
val exclamation = (msg: String) => msg + "!"
val question = (msg: String) => msg + "?"
val awkward = (msg: String) => msg + "..."
val reverse = (msg: String) => msg.reverse

// Je collega heeft daarmee een functie gemaakt die een string aankleed op deze manier:
// ! + msg + ... + ? (een voorbeeld staat hieronder)
// Het probleem is alleen dat het niet heel leesbaar is met twee helper functies.
val helper1 = (msg: String) => question(reverse(msg))
val helper2 = (msg: String) => helper1(exclamation(reverse(msg)))
val decorateString = (msg: String) => helper2(awkward(msg))

decorateString("How are you") // returns "!How are you...?"

// Maak een nieuwe versie van de functie decorateString met function composition, ZONDER de helper
// functies helper1 en helper2 te gebruiken. Gebruik ALLEEN de vier functies exclamation, question,
// awkward en reverse, en functiecompositie.

// (Je mag hem decorateStringBetter o.i.d. noemen om naamconflicten te voorkomen)
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Antwoord op deel a

val decorateStringBetter = (msg: String) =>
  (reverse andThen exclamation andThen reverse andThen awkward andThen question)(msg)

decorateStringBetter("How are you")


// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Deel b (5 begripspunten)
// ================================================================================================
// In het huiswerk heb je een functie geschreven om het totaalbedrag van de rekening in het café
// te berekenen. Deze functie krijgt de strategie waarmee het totaal moet worden berekend mee.
// Dit kan volle prijs zijn, maar ook 10% korting of ieder tweede drankje van dezelfde soort gratis
// tijdens happy hour.

// Een uitwerking van de opgave vind je hieronder.

// LET OP: de amounts en prices zijn nu gegeven in een lijst van tuples, anders dan in de huiswerkopgave!

def totalPrice(tab: List[(Int, Double)], strategy: ((Int, Double)) => Double): Double = {
//  val perDrink = amount zip price
  tab.foldLeft[Double](0)((acc, entry) => {
    val (amount, price) = entry
    strategy(amount, price) + acc
  })
}

def fullPrice(entry: (Int, Double)): Double = {
  val (amount, price) = entry
  amount * price
}

def discount10(entry: (Int, Double)): Double = {
  val (amount, price) = entry
  0.9 * fullPrice(amount, price)
}

def happyHour(entry: (Int, Double)): Double = {
  val (amount, price) = entry
  (amount + 1) / 2 * price
}

var tab = List((2, 2.5), (3, 3.25))
totalPrice(tab, fullPrice) // Geeft 14.75
totalPrice(tab, discount10) // Geeft 13.275
totalPrice(tab, happyHour) // Geeft 9.0

// De eigenaar van het café gaat zijn prijzen verhogen en wil dit niet in zijn database aanpassen,
// maar een extra strategie gebruiken om alle prijzen met 15% te verhogen voordat de kortingstrategie
// wordt toegepast.

// Schrijf de functie totalIncreasedPrice die hetzelfde werkt als totalPrice, maar alle prijzen eerst
// met 15% verhoogd (vermenigvuldigd met 1.15) voordat de strategie wordt toegepast. De signature
// van totalIncreasePrice is hetzelfde als die van totalPrice.

// Schrijf hiervoor eerst een functie increase met signature ((Int, Double)) => (Int, Double) die
// de prijs met 15% verhoogd (en het amount hetzelfde houdt). (De functie slikt dus een tuple!)

// Laat daarna de functie totalIncreasePrice de functie totalPrice aanroepen, maar met een aangepaste
// strategie. Gebruik hierbij functie compositie (function composition).
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Antwoord op deel b

val increase = (price: Double) => 1.15 * price


def totalIncreasePrice(tab: List[(Int, Double)], strategy: ((Int, Double)) => Double): Double = {
  tab.map(t => {
    val (a, p) = t
    (a, increase(p))
  })

//    val perDrink = amount zip price
  tab.foldLeft[Double](0)((acc, entry) => {
    val (amount, price) = entry
    strategy(amount, price) + acc
  })
}


totalIncreasePrice(tab, fullPrice) // Geeft 14.75
totalIncreasePrice(tab, discount10) // Geeft 13.275
totalIncreasePrice(tab, happyHour) // Geeft 9.0


// Om te testen:
//totalIncreasedPrice(tab, fullPrice) // Geeft 16.9625
//totalIncreasedPrice(tab, discount10) // Geeft 15.26625
//totalIncreasedPrice(tab, happyHour)// Geeft 10.35

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++