package Les_1.RXAvans

class Printer[T](private val template: String) extends Observer[T]{
  override def next(data: T): Unit = print(template + data)
}
