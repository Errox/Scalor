package RjAvans

import collection.mutable.ArrayBuffer

trait Observable[T] {
  private var observers = ArrayBuffer[Observer[T]]()

  def observe(observer: Observer[T]): Unit = observers += observer

  def notifyObservers(data: T): Unit = {
    for (o <- observers) {
      o.next(data)
    }
  }
}
