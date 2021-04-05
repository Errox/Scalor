package Les_2. RXAvans

trait Observer[T] {
  def next(data: T)
}
