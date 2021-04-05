package Les_4.RXavans

class Map[T](private val transform: T => T) extends ReactiveLink[String] {
  override def next(data: String): Unit = notifyObservers(data)
}
