package com.android.chesss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private const val TAG = "MainActitvity"

class MainActivity : AppCompatActivity(), ChessDelegate {

    var chessModel = ChessModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "$chessModel")
val chessView = findViewById<ChessView>(R.id.chess_view)
        chessView.chessDelegate = this
    }

    override fun pieceAt(col: Int, row: Int): ChessPiece? {
        return chessModel.pieceAt(col, row)
    }
}