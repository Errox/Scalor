package RjAvans

class Logger[T] extends ReactiveLink[T] {
  override def next(data: T): Unit = {
    println(data)
    notifyObservers(data)
  }
}
