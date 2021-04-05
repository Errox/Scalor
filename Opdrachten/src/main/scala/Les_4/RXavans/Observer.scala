package Les_4.RXavans

trait Observer[T] {
  def next(data: T)
}
