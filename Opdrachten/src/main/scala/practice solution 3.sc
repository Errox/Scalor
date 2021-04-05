// Below is some code that establishes a database connection. In each of the question
// you have to determine whether some technique of functional programming is used somewhere
// in the example code. 

// ************************************************************************************************
// Question 1
// ************************************************************************************************
// Is there a closure in this example?
// If so, which function is a closure?

// Solution
// There is a closure! The lambda that is returned in 'createQueryEngine' closes over 'connection'.


// ************************************************************************************************
// Question 2
// ************************************************************************************************
// Is pattern matching used?
// If so, where is pattern matching used?

// Solution
// There is not pattern matching


// ************************************************************************************************
// Question 3
// ************************************************************************************************
// Is there a recursive function?
// If so, which function is recursive?

// Solution
// There is no recursive function


// ************************************************************************************************
// Question 4
// ************************************************************************************************
// Is there a pure function?
// If so, which function is pure?

// Solution
// There are pure functions. One example is 'createSelectQuery'.


// ************************************************************************************************
// Question 5
// ************************************************************************************************
// Is a function with side effects?
// If so, which function has side effects?

// Solution
// There are functions with side effects. The lambda returned from 'createQueryEngine' has side
// effects. (The output is different even with the same input).


// ************************************************************************************************
// Question 6
// ************************************************************************************************
// Is there a function in curried form?
// If so, which function is in curried form?

// Solution
// There is a function in curried form, namely 'createConnection'.


// ************************************************************************************************
// Question 7
// ************************************************************************************************
// Is partial application used?
// If so, on which function is partial application used?

// Solution
// Partial application is used here: createSelectQuery("*", _)


// ************************************************************************************************
// Question 8
// ************************************************************************************************
// Is function composition used?
// If so, which function is a composition?

// Solution
// Function composition is not used



// Start of the code

// This part is a mock
class Connection{
  def setParams(params: Map[String, String]) {}
  def run(query: String): List[String] = {Nil}
}

object db {
  def connect(url: String) = new Connection()
}
// End of the mock


def createConnection(url: String)(params: Map[String, String]): Connection = {
  val connection = db.connect(url)
  connection.setParams(params)
  connection
}

val createLocalConnection = createConnection("localhost:4080")

def createQueryEngine(query: String) = {
  val connection = createLocalConnection(Map())

  () => {
    connection.run(query)
  }
}

def createSelectQuery(filter: String, table: String): String = {
  "SELECT " + filter + " FROM " + table + ";"
}

val selectAll = createSelectQuery("*", _)

val getUsers = createQueryEngine(selectAll("users"))

getUsers() // returns all users in the 'users' table