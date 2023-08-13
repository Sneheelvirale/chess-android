package com.android.chesss

import android.util.Log
import kotlin.math.log

class ChessModel {
    var piecesBox = mutableSetOf<ChessPiece>()

    init {
        reset()
        //TODO
        Log.d(TAG, "${piecesBox.size}")

        movePiece(0,1,0,1)
     //   movePiece(1,7,5,5)

        Log.d(TAG, toString())
        Log.d(TAG, "${piecesBox.size}")
    }

    fun movePiece(fromcol:Int, fromrow: Int, tocol: Int, torow: Int){
        val movingPiece = pieceAt(fromcol, fromrow) ?:return
        pieceAt(tocol, torow)?.let {
            if (it.player == movingPiece.player){return}
            piecesBox.remove(it)
        }
        movingPiece.col = tocol
        movingPiece.row = torow
    }
     fun reset() {
        piecesBox.removeAll(piecesBox)

        for (i in 0..1) {
            piecesBox.add(ChessPiece(0 + i * 7, 0, ChessPlayer.WHITE, ChessRank.ROOK, R.drawable.wr))
            piecesBox.add(ChessPiece(0 + i * 7,7,ChessPlayer.BLACK,ChessRank.ROOK, R.drawable.br))

            piecesBox.add(ChessPiece(1 + i * 5,0,ChessPlayer.WHITE,ChessRank.KNIGHT, R.drawable.wn))
            piecesBox.add(ChessPiece(1 + i * 5,7,ChessPlayer.BLACK,ChessRank.KNIGHT, R.drawable.bn))

            piecesBox.add(ChessPiece(2 + i * 3,0,ChessPlayer.WHITE,ChessRank.BISHOP, R.drawable.wb))
            piecesBox.add(ChessPiece(2 + i * 3,7,ChessPlayer.BLACK,ChessRank.BISHOP, R.drawable.bb))

        }
        for (i in 0..7){
            piecesBox.add(ChessPiece(i,1,ChessPlayer.WHITE,ChessRank.PAWN,R.drawable.wp))

            piecesBox.add(ChessPiece(i,6,ChessPlayer.BLACK,ChessRank.PAWN, R.drawable.bp))

        }
        piecesBox.add(ChessPiece(3,0,ChessPlayer.WHITE,ChessRank.QUEEN, R.drawable.wq))
        piecesBox.add(ChessPiece(3,7,ChessPlayer.BLACK,ChessRank.QUEEN, R.drawable.bq))

        piecesBox.add(ChessPiece(4,0,ChessPlayer.WHITE,ChessRank.KING, R.drawable.wk))
        piecesBox.add(ChessPiece(4,7,ChessPlayer.BLACK,ChessRank.KING, R.drawable.bk))

    }
 fun pieceAt(col: Int,row: Int) :ChessPiece?{
    for (piece in piecesBox){
        if (col==piece.col && row == piece.row){
            return piece
        }
    }
    return null
}
    override fun toString(): String {
        var desc =" \n"
        for (row in 7 downTo 0) {
            desc += "$row"
            for (col in 0..7) {
                val piece = pieceAt(col, row)
                if (piece == null) {
                    desc += " ."
                }else{
                    val white = piece.player == ChessPlayer.WHITE
                    desc += " "
                    when (piece.rank){
                        ChessRank.KING -> {
                            desc += if (white) "k" else "K"
                        }
                        ChessRank.QUEEN -> {
                            desc += if (white) "q" else "Q"
                        }
                        ChessRank.BISHOP -> {
                            desc += if (white) "b" else "B"
                        }
                        ChessRank.ROOK -> {
                            desc += if (white) "r" else "R"
                        }
                        ChessRank.KNIGHT -> {
                            desc += if (white) "n" else "N"
                        }
                        ChessRank.PAWN -> {
                            desc += if (white) "p" else "P"
                        }
                    }
                }
            }
            desc += "\n"
        }
        desc += "  0 1 2 3 4 5 6 7"
        return desc
    }
}