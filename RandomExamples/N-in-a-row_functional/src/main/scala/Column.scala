import Cell._

package object Column {
  type Column = List[CellState]
  type Row = Column
  type Diag = Column

  def createEmpty(): Column = {
    List.fill[CellState](6)(Empty)
  }

  def play(column: Column, state: CellState): Column = {
    column match {
      case Nil => Nil
      case Empty :: xs => state :: xs
      case x :: xs => x :: play(xs, state)
    }
  }
}
