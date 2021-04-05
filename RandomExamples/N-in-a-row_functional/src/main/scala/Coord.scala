package object coord {
  type Coord = (Int, Int)

  def step(diff: Coord)(coord: Coord): Coord = {
    val (di, dj) = diff
    val (i,j) = coord

    (i+di, j+dj)
  }

  def left: ((Int, Int)) => (Int, Int) = step((0, -1))
  def right: ((Int, Int)) => (Int, Int) = step((0, 1))
  def up: ((Int, Int)) => (Int, Int) = step((1,0))
  def down: ((Int, Int)) => (Int, Int) = step((-1, 0))
}
