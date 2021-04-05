// ************************************************************************************************
// Question 7 of 7 (3 points total)
//
// This question has 1 part
// ************************************************************************************************

// Here we have defined a function makeModifier that takes a modifying strategy and applies it to
// the string that is passed to it.
def makeModifier(mod: String => String): String => String = {
  msg: String => mod(msg)
}

// We can use this function to create a function that adds a period to the end of the string like
// so.
val period = (msg: String) => msg + "."
val addPeriod = makeModifier(period)

// This is how the function then works.
val message: String = "Hello there"
addPeriod(message) // "Hello there."

// ================================================================================================
// Question: Write three strategies that alter the string in the following manner:
//   - A function addExclamation that adds an exclamation mark at the end:
//     "Hello there" -> "Hello there!"

//   - A function addQuestion that adds a question mark in front and at the end:
//     "Hello there" -> "?Hello there?"

//   - A function addInterrobang that applies both preceding strategies. Use the two functions
//     addQuestion and addExclamation to construct this function. Example:
//     "Hello there" -> "?Hello there?!"
// ================================================================================================

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Your answer here

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
