object RxAvans {
  type Observer[A] = A => Unit
  type Observable[A] = Observer[A] => Unit

  def map[A, B](obs: Observable[A], transform: A => B): Observable[B] = {
    (observer: Observer[B]) => obs(transform andThen observer)
  }

  def filter[A](obs: Observable[A], conforms: A => Boolean): Observable[A] = {
    (observer: Observer[A]) => obs((data: A) => if (conforms(data)) observer(data))
  }

  def log[A](obs: Observable[A]): Observable[A] = {
    (observer: Observer[A]) => obs((data: A) => {
      println(data)

      observer(data)
    })
  }

  def fork[A](obs: Observable[A]): Observable[A] = {
    var mem: List[A] = Nil

    (observer: Observer[A]) =>
      mem match {
        case Nil => obs((data: A) => {
          mem = mem :+ data
          observer(data)
        })
        case _ => {
          mem.foreach(observer)
        }
      }
  }
}
