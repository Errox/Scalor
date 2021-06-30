// ************************************************************************************************
// Vraag 2 van 5 (21 van 50 begripspunten totaal)
//
// Deze vraag bestaat uit 7 delen
// ************************************************************************************************

// Je werkt als game developer en je collega heeft je het stukje code gegeven dat je hieronder vindt.
// Jouw taak is om uit te vinden welke functioneel programmeren technieken er in zitten.

// Elk van de onderstaande vragen gaan over een techniek van functioneel programmeren. De vraag is
// steeds of deze techniek voorkomt in de code onderaan de vraag. Als de techniek voorkomt moet
// je ook aangeven waar (regel / functienaam / variabelenaam / etc) hij voorkomt.

// BEGIN CODE
def createDungeon(createEnemy:()=> String, createWall: () => String, rows: Int, columns: Int): String = {
  var dungeon = "\n"
  val randomGen = scala.util.Random

  var previousSpotContainedEnemy = false
  for(row <- 1 to rows){
    for(col <- 1 to columns) {
      col match {
        case _ if row == 1 || row == rows => dungeon += "---"
        case c if c == 1 || c == columns => dungeon += createWall()
        case _ if !previousSpotContainedEnemy && randomGen.nextInt(100) < 50 => {
          dungeon += createEnemy()
          previousSpotContainedEnemy = true
        }
        case _ => {
          dungeon += "   "
          previousSpotContainedEnemy = false
        }
      }
    }
    dungeon += "\n"
  }
  dungeon
}

def sampleFromList(list: List[String]): () => String = {
  val randomGen = scala.util.Random
  () => list(randomGen.nextInt(list.size))
}

val enemySampler = sampleFromList(List("@_@","O_O", "-_-"))
val wallSampler = sampleFromList(List("|||","|\\|", "|/|"))

def createLevelOneMaze = createDungeon(enemySampler, wallSampler, _, _)

createLevelOneMaze(10, 10)
// EINDE CODE

// ================================================================================================
// Deel a (3 begripspunten)
// ================================================================================================
// Wordt pattern matching toegepast?
// Indien ja, waar wordt het toegepast?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Antwoord op deel a

// Ja, vanaf regel 22 t/m 32 op de variable "col"

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Deel b (3 begripspunten)
// ================================================================================================
// Is er een recursieve functie?
// Indien ja, welke functie is recursief?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Antwoord op deel b

// Er wordt in deze code geen gebruik gemaakt van recursief

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Deel c (3 begripspunten)
// ================================================================================================
// Is er een pure functie?
// Indien ja, welke functie is puur?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Antwoord op deel c

// Het ligt eraan hoe het bekijkt. De methodes die aangemaakt zijn bevatten een
// random variable die de de methodes dus niet puur maakt.
// Echter is het wel zo dat er op regel 42 een flat arrow gebruikt wordt. Deze functie maakt gebruik van de variabel randomGen
// Als voor deze falt arrow de list en random input hetzelfde is, zal deze hetzelfde resultaat teruggeven. Wat het dan wel een pure functie maakt.

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Deel d (3 begripspunten)
// ================================================================================================
// Is er een functie met neveneffecten (side effects)?
// Indien ja, welke functie heeft neveneffecten?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Antwoord op deel d
 // De functie createDungeon heeft neveneffecten

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Deel e (3 begripspunten)
// ================================================================================================
// Is er een functie in gecurryde vorm (curried form)?
// Indien ja, welke functie is in gecurryde vorm?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Antwoord op deel e
// Er zitten geen gecurryde functie in de code

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Deel f (3 begripspunten)
// ================================================================================================
// Wordt partial application toegepast?
// Indien ja, op welke functie wordt partial application toegepast?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Antwoord op deel f

// Ja, op regel bij de functie "createLevelOneMaze" hier worden eerst 2 parameter
// ingevuld bij "createDungeon" en de overige 2 worden op gevuld met een _
// Deze waardes worden pas op regel 50 aan gevuld

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Deel g (3 begripspunten)
// ================================================================================================
// Wordt functie compositie (function composition) toegepast?
// Indien ja, welke functie is een compositie?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part g
  // Er wordt in deze code geen gebruik gemaakt van functie compositie

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



