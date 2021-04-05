import Board._
import Cell._


package Winner {
  sealed trait WinState {
    def toString: String
  }

  object NoWinner extends WinState {
    override def toString: String = "No one has won"
  }

  object OWinner extends WinState {
    override def toString: String = "Congrats O!"
  }

  object XWinner extends WinState {
    override def toString: String = "Congrats X!"
  }
}

package object Winner {
  def combine(a: WinState, b: WinState): WinState = {
    (a, b) match {
      case (XWinner, _) => XWinner
      case (_, XWinner) => XWinner
      case (OWinner, _) => OWinner
      case (_, OWinner) => OWinner
      case (_, _) => NoWinner
    }
  }

  def winner(board: Board): WinState = {
    val colWinner = board.foldLeft[WinState](NoWinner)((acc, col) => combine(acc, winnerInList(col)) )
    val rowWinner = transpose(board).foldLeft[WinState](NoWinner)((acc, col) => combine(acc, winnerInList(col)) )
    val diagWinner = diagonals(board).map(winnerInList(_)).foldLeft[WinState](NoWinner)(combine(_, _))

    combine(combine(colWinner, rowWinner), diagWinner)
  }

  def winnerInList(list: List[CellState], state: CellState = Empty, count: Int = 0): WinState = {
    (list, state, count) match {
      case (_, s, 3) => toWinState(s)
      case (Nil, _, _) => NoWinner
      case (Empty :: xs, _, _) => winnerInList(xs, Empty, 0)
      case (x :: xs, s, _) if x == s => winnerInList(xs, x, count + 1)
      case (x :: xs, s, _) if x != s => winnerInList(xs, x, 0)
    }
  }
}




