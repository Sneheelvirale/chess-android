package com.android.chesss

data class ChessPiece(var col: Int, var row: Int, val player: ChessPlayer, val rank: ChessRank, val resID: Int) {
}