scala.language.postfixOps

// Lecture 2


// 1
// Write a function swap(tuple: (Int, String)) => (String, Int) that takes a tuple (Int, String)
// and swaps them around using tuple deconstruction
def swap(tuple: (Int, String)): (String, Int) ={
  val (x,y) = tuple
  (y, x)
}
swap((2,"yeet"));


// 2
// Write a function doubleHead(list: List[Int]) => List[Int] that takes a list,
// and returns that same list, but with the first element multiplied by two. Use list deconstruction.
def doubleHead(list: List[Int]): List[Int] ={

  val fv: Int = list.head * 2;
  val lv: List[Int] = list.tail
  fv :: lv;

}

doubleHead(List(9, 2, 3, 4, 510));


// 3
// Write a function add with signature Int => Int => Int that returns a function which returns x + y
val doubleIt = (x: Int, y: Int) => x + y

def funcWithFuncParam(number1: Int, number2: Int, aFunction: (Int, Int) => Int): Int = {
  aFunction(number1, number2)
}

funcWithFuncParam(1, 2, doubleIt)

//shorter
def add(x: Int): Int => Int = (y: Int) => x + y

// Here we have a more signature clearer solution
val addAlternative: Int => (Int => Int) =
  (x: Int) => (y: Int) => x + y

add(3)(5)
val addTwo = add(2)
addTwo(1)
addTwo(2)


// 9
val amount = List(2,3)
val price = List(3.0, 2.5)

// Two times the same action.
amount zip price
amount.zip(price)


val full = (a : Int, p: Double) => a * p
val tenPercent = (a: Int, p: Double) => 0.9 * a * p
val happyHour = (a: Int, p: Double) => (a+1) / 2 * p



def calcPrice(amount: List[Int],
              price: List[Double],
              strategy: (Int, Double) => Double) ={
//  amount zip price map(t => {
//    val (a, p) = t // Destructing / deconstruction
//    println(a, p)
//  }) sum

  amount.zip(price).foldLeft(0.0)((s,t) => s + strategy.tupled(t))
}

calcPrice(amount, price, full)