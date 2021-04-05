package Les_1.RXAvans

trait Observer[T] {
  def next(data: T)
}
