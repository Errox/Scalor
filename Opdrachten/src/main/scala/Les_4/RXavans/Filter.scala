package Les_4.RXavans

class Filter[T](private val conforms: T => Boolean) extends ReactiveLink[T] {
  override def next(data: T): Unit = if(conforms(data)) notifyObservers(data)
}
