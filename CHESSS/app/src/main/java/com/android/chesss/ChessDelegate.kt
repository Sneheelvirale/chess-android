package com.android.chesss

interface ChessDelegate {
    fun pieceAt(col: Int,row: Int) :ChessPiece?
    fun movePiece(fromcol:Int, fromrow: Int, tocol: Int, torow: Int)
}