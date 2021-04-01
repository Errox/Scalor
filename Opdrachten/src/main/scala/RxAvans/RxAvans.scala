package object RxAvans {
  type Observer[A] = A => Unit
  type Observable[A] = Observer[A] => Unit

  def newObservable[A](obs: Observable[A]): Observable[A] = {
    (observer: Observer[A]) => obs(observer)
  }

  def map[A](obs: Observable[A], f: A => A): Observable[A] = {
    (observer: Observer[A]) => obs(f andThen observer)
  }

  def filter[A](obs: Observable[A], f: A => Boolean): Observable[A] = {
    (observer: Observer[A]) => obs((value: A) => if (f(value)) observer(value))
  }

  def log[A](obs: Observable[A]) = {
    (observer: Observer[A]) =>
      obs((value: A) => {
        println(value)
        observer(value)
      })
  }

  def fork[A](obs: Observable[A]) = {
    var mem: List[A] = Nil
    var executed = false

    val memCall = (observer: A => Unit) => {
      if (!executed) {
        obs((value: A) => {
          mem = value :: mem
          observer(value)
        })
      } else {
        mem.reverse.foreach(observer)
      }

      executed = true
    }

    (newObservable[A](memCall), newObservable[A](memCall))
  }

}
