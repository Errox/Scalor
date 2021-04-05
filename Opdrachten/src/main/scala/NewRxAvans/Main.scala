import RxAvans._

object Main extends App {
  val randomData: Observable[Option[String]] = (observer: Option[String] => Unit) => {
    println("fetching...")
    io.Source.fromURL("https://randomuser.me/api/?format=csv&results=10&inc=name,nat,email")
      .getLines
      .drop(1)
      .foreach(
        person => if (math.random() > 0.25) observer(Some(person)) else observer(None)
      )
  }

  val loggedData = log(randomData)

  val someFiltered = filter(loggedData, (opt: Option[String]) => opt match {
    case Some(_) => true
    case _ => false
  })

  val someExtracted = map(someFiltered, (opt: Option[String]) => opt.get)

  val splitter = fork(someExtracted)

  val mister = filter(splitter, (entry: String) => entry.contains("Mr"))
  val miss = filter(splitter, (entry: String) => entry.contains("Miss"))

  val getFirstName: String => String = _.split(",")(1)

  val misterFirst = map(mister, getFirstName)
  val missFirst = map(miss, getFirstName)

  misterFirst(value => println("mister " + value))
  missFirst(value => println("miss " + value))
}