// ************************************************************************************************
// Question 1 of 7 (3 points total)
//
// This question has 3 parts
// ************************************************************************************************

// ================================================================================================
// Question: For each of the following snippets answer the following questions:
// Is there a closure?
// If the answer is yes, which function is the closure and what variable is closed over?
// ================================================================================================



// ================================================================================================
// Snippet 1 (1 point)
// ================================================================================================
def concatTwiceA(lhs: String, rhs: String): String = {
  lhs + lhs + rhs + rhs
}

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to snippet 1
// Not a closure.
// The expression 'lhs + lhs + rhs + rhs' in the function body is evaluated when the function is called.
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Snippet 2 (1 point)
// ================================================================================================
def concatTwiceB(lhs: String, rhs: String): () => String = {
  () => lhs + lhs + rhs + rhs
}

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to snippet 2
// The lambda that is returned is a closure. It closes over the variables 'lhs' and 'rhs'.
// The expression 'lhs + lhs + rhs + rhs' in the function body is evaluated when the lambda is called.
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


// ================================================================================================
// Snippet 3 (1 point)
// ================================================================================================
def concatTwiceC(lhs: String): String => String = {
  (rhs: String) => lhs + lhs + rhs + rhs
}

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to snippet 3
// The lambda that is returned is a closure. It closes over the variable 'lhs'.
// The expression 'lhs + lhs + rhs + rhs' in the function body is evaluated when the lambda is called.
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
