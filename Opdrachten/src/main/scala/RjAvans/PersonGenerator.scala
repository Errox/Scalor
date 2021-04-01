package RjAvans

class PersonGenerator(private val url: String) extends Observable[String] {
  def trigger() = {
    println("fetching...")
    val input = scala.io.Source.fromURL(url).getLines().drop(1)
    while (input.hasNext) {
      notifyObservers(input.next())
    }
  }
}
