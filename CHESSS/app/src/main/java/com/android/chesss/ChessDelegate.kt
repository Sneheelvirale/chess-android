package com.android.chesss

interface ChessDelegate {
    fun pieceAt(col: Int,row: Int) :ChessPiece? }