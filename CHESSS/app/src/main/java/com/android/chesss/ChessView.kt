package com.android.chesss

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private final val originX = 20f
    private final val originY = 200f
    private final val cellSide = 130f
    private final val imgResIDs = setOf(R.drawable.
    bb,
        R.drawable.wb,
        R.drawable.bk,
        R.drawable.wk,
        R.drawable.bq,
        R.drawable.wq,
        R.drawable.br,
        R.drawable.wr,
        R.drawable.bn,
        R.drawable.wn,
        R.drawable.bp,
        R.drawable.wp,
    )
    private final val bitmaps = mutableMapOf<Int, Bitmap>()
    private final val paint = Paint()
    init {
        loadBitmaps()
    }

    override fun onDraw(canvas: Canvas?) {
        drawChessboard(canvas)
        drawPieces(canvas)

    }
    private fun drawPieces(canvas: Canvas?){
        val chessModel = ChessModel()
        chessModel.reset()

        for (row in 0..7){
            for (col in 0..7){
                chessModel.pieceAt(col, row)?.let { drawPieceAt(canvas, col, row, it.resID)
                }
            }
        }
    //    drawPieceAt(canvas, 0,7,R.drawable.wr)
      //  drawPieceAt(canvas, 0,6,R.drawable.wp)


    }
    private fun drawPieceAt(canvas: Canvas?, col: Int,row: Int, resID: Int) {
        val bitmap = bitmaps[resID]!!
        canvas?.drawBitmap(bitmap,
            null, RectF(originX + col * cellSide,originY + ( 7 -row ) * cellSide,originX + (col + 1)  * cellSide,originY + (( 7 - row ) +1)  * cellSide), paint)


    }
    private fun loadBitmaps(){
        imgResIDs.forEach  {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }
    private fun drawChessboard(canvas: Canvas?){
        for (i in 0..7) {

            for (j in 0..7) {
                paint.color = if ((i + j) % 2 == 1)Color.LTGRAY
                else Color.DKGRAY
                canvas?.drawRect(
                    originX + i * cellSide,
                    originY + j * cellSide,
                    originX + ( i + 1) * cellSide,
                    originY + (j + 1) * cellSide,
                    paint
                )
            }
        }
    }
}