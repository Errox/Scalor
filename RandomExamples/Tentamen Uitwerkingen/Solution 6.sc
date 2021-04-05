// ************************************************************************************************
// Question 6 of 7 (4 points)
//
// This question has 1 part
// ************************************************************************************************

// We'll write a special kind of observable in this question. This observable
// replays all values that were published before when a new function is subscribed.
// The function createReplayObservable will return a tuple with two functions, one
// for subscribing and one for publishing.
// You CANNOT use any for / while / do loops! Use higher order functions instead.
// You may use functions with side effects.

// This is an example of how it should work:

//val (sub, pub) = createReplayObservable()

//pub(0)
//pub(1)
//sub(observerA)   // prints 'A received 0' and 'A received 1'
//pub(2)           // prints 'A received 2'
//sub(observerB)   // prints 'B received 0', 'B received 1' and 'B received 2'
//pub(3)           // prints 'A received 3' and 'B received 3'

val observerA = (value: Int) => println(s"A received $value")
val observerB = (value: Int) => println(s"B received $value")

// ================================================================================================
// Question: Write the function createReplayObservable. You can test whether it works with the
// commented out example above.
// ================================================================================================

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Your answer here
def createReplayObservable(): ((Int => Unit) => Unit , Int => Unit) = {
  var subscribers: Array[Int => Unit] = Array()
  var memory: List[Int] = Nil

  val subscribe: (Int => Unit) => Unit = (f: Int => Unit) => {
    subscribers = subscribers :+ f
    memory.reverse.foreach(f)
  }

  val publish: Int => Unit = (value: Int) => {
    memory = value :: memory
    subscribers.foreach(subscriber => subscriber(value))
  }

  (subscribe, publish)
}

val (sub, pub) = createReplayObservable()

pub(0)
pub(1)
sub(observerA)   // prints 'A received 0' and 'A received 1'
pub(2)           // prints 'A received 2'
sub(observerB)   // prints 'B received 0', 'B received 1' and 'B received 2'
pub(3)           // prints 'A received 3' and 'B received 3'


// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
