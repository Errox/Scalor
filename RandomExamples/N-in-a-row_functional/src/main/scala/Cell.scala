import Winner._


package Cell {
  sealed trait CellState {
    def toString: String
  }

  object Empty extends CellState {
    override def toString: String = "."
  }

  object X extends CellState {
    override def toString: String = "X"
  }

  object O extends CellState {
    override def toString: String = "O"
  }
}


package object Cell {
  def flip(state: CellState): CellState = {
    state match {
      case Empty => Empty
      case X => O
      case O => X
    }
  }

  def toCellState(state: WinState): CellState = {
    state match {
      case NoWinner => Empty
      case XWinner => X
      case OWinner => O
    }
  }

  def toWinState(state: CellState): WinState = {
    state match {
      case Empty => NoWinner
      case X => XWinner
      case O => OWinner
    }
  }
}
