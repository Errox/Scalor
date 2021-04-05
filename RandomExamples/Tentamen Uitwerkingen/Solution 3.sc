// ************************************************************************************************
// Question 3 of 7 (5 points total)
//
// This question has 4 parts
// ************************************************************************************************

// You are given a list of students and their study result. This question is about finding
// information in this data set using functional programming techniques


// The student class contains name and statistics on their result
class Student(val firstName: String, val lastName: String,
              val lowestResult: Double, val averageResult:Double, val highestResult:Double) {

  override def toString = {
    firstName + " " + lastName
  }
}

// The data set itself
val students = List(
  new Student("Alex", "McDougal", 2, 6, 6),
  new Student("Dion", "Kuiper", 9, 9.5, 10),
  new Student("Ruud", "Schellius", 6, 7, 7.5),
  new Student("Peter", "Beer", 5.5, 5.5, 5.5)
)


// ================================================================================================
// Part 1 (1 point)
// Use higher order functions to answer the following question. No for loops allowed.

// Which students have a an average result higher than 7?
// ================================================================================================

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 1
students.filter(_.averageResult > 7)


// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



// ================================================================================================
// Part 2 (1 point)
// Use higher order functions to answer the following question. No for loops allowed.

// Which students have an average result that is equal to their lowest grade?
// ================================================================================================

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 2
students.filter((s) => s.averageResult == s.lowestResult)


// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



// ================================================================================================
// Part 3 (1 point)
// Use higher order functions to answer the following question. No for loops allowed.

// What's average of all the highest result of all students?
// ================================================================================================

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 3
// Each of these lines is a solution

students.foldLeft(0d)(_ + _.highestResult) / students.size

students.map(_.highestResult).reduce(_+_) / students.size

students.map(_.highestResult).sum / students.size


// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



// ================================================================================================
// Part 4 (2 point)
// In this part you are given some code that determines whether two students work well together.
// The students work well together in two cases
//   - If the highest result of either student is at least 8
//   - If the difference between their average result is less than 2
// To find the students that well together we wrote two functions, one for each case. You can find
// these functions below.

// Question: The two functions contain a lot of duplicate code. Refactor them into one higher order
// function. Write this function in the answer box below. You can test that it works the same as
// the separate functions.


// CODE STARTS HERE

// Students work well together if the highest result of either students is at least 8.
def studentsWhoWorkWellTogether(students: List[Student]):List[(Student, Student)] = {
  var studentsWhoWorkWellTogether: List[(Student, Student)] = Nil

  for(student1 <- students){
    for(student2 <- students){
      if(student1 != student2){
        if(student1.highestResult >= 8 || student2.highestResult >= 8)
          studentsWhoWorkWellTogether ::= (student1, student2)
      }
    }
  }
  studentsWhoWorkWellTogether
}

// Students work well together if the difference between their average result is less than 2
def studentsWhoWorkWellTogether2(students: List[Student]):List[(Student, Student)] = {
  var studentsWhoWorkWellTogether: List[(Student, Student)] = Nil

  for(student1 <- students){
    for(student2 <- students){
      if(student1 != student2){
        if(Math.abs(student1.averageResult - student2.averageResult) < 2)
          studentsWhoWorkWellTogether ::= (student1, student2)
      }
    }
  }
  studentsWhoWorkWellTogether
}

studentsWhoWorkWellTogether(students)
studentsWhoWorkWellTogether2(students)
// ================================================================================================

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// Answer to part 4

def studentsWhoWorkWellTogether(students: List[Student], doWorkWellTogether:(Student, Student) => Boolean ): List[(Student, Student)] = {
  var studentsWhoWorkWellTogether: List[(Student, Student)] = Nil

  for(student1 <- students){
    for(student2 <- students){
      if(student1 != student2){
        if(doWorkWellTogether(student1, student2))
          studentsWhoWorkWellTogether ::= (student1, student2)
      }
    }
  }
  studentsWhoWorkWellTogether
}

studentsWhoWorkWellTogether(students, (s1:Student, s2: Student) => s1.highestResult >= 8 || s2.highestResult >= 8 )
studentsWhoWorkWellTogether(students, (s1:Student, s2: Student) => Math.abs(s1.averageResult - s2.averageResult) < 2)






// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
