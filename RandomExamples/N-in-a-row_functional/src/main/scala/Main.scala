import Board._
import Cell._
import Winner._

import scala.annotation.tailrec


package object Game {
  @tailrec
  def gameLoop(board: Board, state: CellState): Unit = {
    show(board)
    println(state + "! Your next move?")
    val col = scala.io.StdIn.readInt() - 1

    val newBoard = Board.play(board, state, col)

    winner(newBoard) match {
      case NoWinner => gameLoop(newBoard, flip(state))
      case w => {show(newBoard); println(w)}
    }
  }
}

object Main extends App {
  var b: Board = Board.createEmpty()

  Game.gameLoop(b, O)
}
