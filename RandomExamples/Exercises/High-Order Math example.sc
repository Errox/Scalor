//A high order function is a function that takes a value and a function as parameter

  /* For the following function; x & y are values for the math function and it's given a function called f
      F also takes two doubles as value and it's return value is a double as seen by => Double
      Everything after the fat arrow / and : is the return of our math function
      So : Double = f(x, y) is our return here.
   */
  def math(x: Double, y: Double, f: (Double, Double) => Double): Double = f(x, y)

  math(50, 20, (x, y) => x+y)


