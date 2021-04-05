// ************************************************************************************************
// Question 4 of 7 (7 points total)
//
// This question has 7 parts
// ************************************************************************************************

// You are working as a game dev and your colleague gave you the piece of code at the end of the
// question. Your task is to find out what functional programming techniques were used.

// Each of the questions that follow ask whether a certain functional programming technique is
// present in the code. If it is you have to point out where you found it.

// ================================================================================================
// Part 1 (1 point)
// ================================================================================================
// Is pattern matching used?
// If so, where is pattern matching used?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 1
// Pattern matching is used in the function 'createDungeon' starting with the keyword 'match'

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Part 2 (1 point)
// ================================================================================================
// Is there a recursive function?
// If so, which function is recursive?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 2
// There is no recursive function

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Part 3 (1 point)
// ================================================================================================
// Is there a pure function?
// If so, which function is pure?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 3
// There is no pure function. All functions use a random generator and have different output
// with the same input.

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Part 4 (1 point)
// ================================================================================================
// Is there a function with side effects?
// If so, which function has side effects?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 4
// We have decided to give everyone the point for this question, as the internals of random
// number generators were not part of this course.

// Since all functions use a random generator they all change state in the random number generator.
// Therefore all functions have side effects.

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Part 5 (1 point)
// ================================================================================================
// Is there a function in curried form?
// If so, which function is in curried form?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 5
// There is no function in curried form

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Part 6 (1 point)
// ================================================================================================
// Is partial application used?
// If so, on which function is partial application used?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 6
// Partial application is used here: 'createDungeon(enemySampler, wallSampler, _, _)'

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Part 7 (1 point)
// ================================================================================================
// Is function composition used?
// If so, which function is a composition?
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 7
// Function composition is not used

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// CODE STARTS HERE
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
