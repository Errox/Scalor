package Les_2.RXAvans

import scala.collection.mutable.ArrayBuffer

trait Observable[T] {
  private var observers = ArrayBuffer[Observer[T]]()

  def observe (observer: Observer[T]): Unit = observers += observer

  def notifyObservers(data: T) = observers.foreach(_.next(data))
}
