

// Lecture 2


// 1
// Write a function swap(tuple: (Int, String)) => (String, Int) that takes a tuple (Int, String)
// and swaps them around using tuple deconstruction
def swap(tuple: (Int, String)): (String, Int) ={
  val (x,y) = tuple
  (y, x )
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

