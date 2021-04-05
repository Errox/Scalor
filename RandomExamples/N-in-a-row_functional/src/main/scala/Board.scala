import Column._
import Cell._


package object Board {
  type Board = List[Column]

  def createEmpty(): Board = {
    List.fill[Column](6)(Column.createEmpty())
  }

  def play(board: Board, state: CellState, col: Int): Board = {
    col match {
      case 0 => Column.play(board.head, state) :: board.tail
      case _ => board.head :: play(board.tail, state, col-1)
    }
  }

  def bottomRow(board: Board): Row = {
    board match {
      case Nil => List()
      case x :: xs => x.head :: bottomRow(xs)
    }
  }

  def topRow(board: Board): Row = {
    board match {
      case Nil => List()
      case x :: xs => x.reverse.head :: topRow(xs)
    }
  }

  def topPart(board: Board): Board = {
    board.map(col => col.tail)
  }

  def transpose(board: Board): Board = {
    board.head match {
      case Nil => List()
      case _ => bottomRow(board) :: transpose(topPart(board))
    }
  }

  def showDirect(board: Board): Unit = {
    board match {
      case Nil => Unit
      case _ => {println(board.head.foldLeft("")(_ + _)); showDirect(board.tail)}
    }
  }

  def show(board: Board): Unit = {
//    board.foreach {println(_)}
    showDirect(transpose(board).reverse)
  }

  def diagonal(board: Board): List[CellState] = {
    board match {
      case Nil => List()
      case x :: xs => x.head :: diagonal(topPart(xs))
    }
  }

  def offDiagonals(board: Board): List[List[CellState]] = {
    board match {
      case Nil => List()
      case x :: xs => diagonal(board) :: offDiagonals(xs)
    }
  }

  def diagonals(board: Board): List[List[CellState]] = {
    offDiagonals(board) ::: offDiagonals(transpose(board)) :::
    offDiagonals(board.reverse) ::: offDiagonals(transpose(board.reverse))
  }
}

