package Les_4.RXavans

trait ReactiveLink[T] extends Observable[T] with Observer[T] {
  def next(data: T)
}
