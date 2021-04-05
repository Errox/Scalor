import scala.math._

// Basics 2
sqrt(sqrt(3))

// Basics 3
// Val isn't mutable
// var is

// Basic 4
// Res objects are always val's

// Basic 5
"Avans" * 3;

// Basic 6
// Max means it'll find the highest number between two ints/doubles
10 max 12


// Functions 1
// Write a function succ(x: Int) that returns the next number. So succ(8) = 9
def succ(x: Int): Int ={
  x+1;
}
succ(8)

// Functions 2
// Write functions max(x: Int ,y: Int) that returns x or y, whichever is highest.
def max(x: Int, y: Int): Int ={
  x max y
}

max(4, 10)

//Function 3
// Write a function doubleMe(x: Int) that returns x + x
def doubleMe(x: Int): Int ={
  x + x
}

doubleMe(5)

//Function 4
//Write a function doubleUs(x: Int, y: Int) that returns doubleMe(x) + doubleMe(y)
def doubleUs(x: Int, y: Int): Int ={
  doubleMe(x) + doubleMe(y);
}

doubleUs(5,10)

//Function 5
//Write a function doubleSmallNumber(x: Int) that
def doubleSmallNumber(x: Int): Int={
  if (x > 100){
    x
  }else{
    doubleMe(x)
  }
}

doubleSmallNumber(2)