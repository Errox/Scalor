// ************************************************************************************************
// Question 2 of 7 (3 points)
//
// This question has 1 part
// ************************************************************************************************


// We'll write a function 'counterFactory' that creates a counter of type integer
// that can be accessed from different functions.
// To make the idea clear, this is what the counter factory should do:
// val getCounter = counterFactory()
//
// val counter1 = getCounter()
// val counter2 = getCounter()
//
// counter1() // 1
// counter2() // 2
// counter1() // 3
// counter2() // 4
// counter1() // 5
// counter2() // 6

// Both 'counter1' and 'counter2' are functions with signature () => Int that return the next count
// and they belong to the same counter of type integer!

// ================================================================================================
// Question: write the function 'counterFactory' with signature () => (() => Int) and show that it
// has the requested output.
// ================================================================================================


// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Your answer here

def counterFactory(): () => () => Int = {
  // THIS NEEDS IMPLEMENTATION
  var count = 0

  def getCounter(): () => Int = () => {
    count += 1
    count
  }

  getCounter
}

val getCounter = counterFactory()

val counter1 = getCounter()
val counter2 = getCounter()

counter1() // 1
counter2() // 2
counter1() // 3
counter2() // 4
counter1() // 5
counter2() // 6

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++