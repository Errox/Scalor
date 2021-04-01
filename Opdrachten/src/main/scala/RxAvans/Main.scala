package RxAvans
// This package contains the Functional solution to RxAvans
object Main extends App {
  val randomData: Observable[String] = newObservable((observer: String => Unit) => {
    println("Fetching...")
    scala.io.Source.fromURL("https://randomuser.me/api/?format=csv&results=10&inc=name,nat,email").mkString.split("\n").drop(1).foreach(observer)
  })

  val loggedData = log(randomData)

  val (left, right) = fork(loggedData)

  val mister = filter[String](left, _.contains("Mr"))
  val miss = filter[String](right, _.contains("Miss"))

  val getFirstName: String => String = _.split(",")(1)

  val misterFirst = map(mister, getFirstName)
  val missFirst = map(miss, getFirstName)

  misterFirst((value) => println("Mister " + value))
  missFirst((value) => println("miss " + value))

}