package Les_2. RXAvans

trait ReactiveLink[T] extends Observable[T] with Observer [T]{
  def next(data: T)
}
