package Les_3. RXAvans

trait Observer[T] {
  def next(data: T)
}
