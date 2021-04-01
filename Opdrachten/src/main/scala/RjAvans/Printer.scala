package RjAvans
class Printer[T](private val template: String) extends Observer[T] {
  override def next(data: T): Unit = println(template + data)
}
