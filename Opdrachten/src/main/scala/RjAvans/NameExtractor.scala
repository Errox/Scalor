package RjAvans

class NameExtractor extends ReactiveLink[String] {
  override def next(data: String): Unit = notifyObservers(data.split(",")(1))
}
