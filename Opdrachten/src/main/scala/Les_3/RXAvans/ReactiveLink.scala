package Les_3. RXAvans

trait ReactiveLink[T] extends Observable[T] with Observer [T]{
  def next(data: T)
}
