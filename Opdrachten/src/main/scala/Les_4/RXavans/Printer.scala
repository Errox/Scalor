package Les_4.RXavans

class Printer[T](private val template: String) extends Observer[T]{
  override def next(data: T): Unit = print(template + data)
}
