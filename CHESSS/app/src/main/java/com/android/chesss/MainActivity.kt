package com.android.chesss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

const val TAG = "MainActitvity"

class MainActivity : AppCompatActivity(), ChessDelegate {

   private var chessModel = ChessModel()
    private lateinit var chessView: ChessView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         chessView = findViewById<ChessView>(R.id.chess_view)
        chessView.chessDelegate = this
        findViewById<Button>(R.id.reset_button).setOnClickListener {
            chessModel.reset()
            chessView.invalidate()
        }
    }

    override fun pieceAt(col: Int, row: Int): ChessPiece? {
        return chessModel.pieceAt(col, row)
    }

    override fun movePiece(fromcol: Int, fromrow: Int, tocol: Int, torow: Int) {
        chessModel.movePiece(fromcol, fromrow, tocol,torow)

            chessView.invalidate()
    }
}