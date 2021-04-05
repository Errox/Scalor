package Les_4.RXavans

class PersonGenerator(private val url: String) extends Observable[String]{
  def trigger() ={
    print("fetching...")
    val input = scala.io.Source.fromURL(url).getLines().drop(1).foreach(notifyObservers)
  }
}
