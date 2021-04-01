package RjAvans

trait Observer[T] {
  def next(data: T)
}
