package com.android.chesss

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private final val originX = 20f
    private final val originY = 200f
    private final val cellSide = 130f
    private final val imgResIDs = setOf(R.drawable.
    black_bishop,
        R.drawable.white_bishop,
        R.drawable.black_king,
        R.drawable.white_king,
        R.drawable.black_queen,
        R.drawable.white_queen,
        R.drawable.black_rook,
        R.drawable.white_rook,
        R.drawable.black_knight,
        R.drawable.white_knight,
        R.drawable.black_pawn,
        R.drawable.white_pawn
    )
    private final val bitmaps = mutableMapOf<Int, Bitmap>()
    private final val paint = Paint()
    init {
        loadBitmaps()
    }

    override fun onDraw(canvas: Canvas?) {
        drawChessboard(canvas)

        val whiteQueenBitmap = bitmaps[R.drawable.white_queen]!!
        canvas?.drawBitmap(whiteQueenBitmap, null, Rect(0,0,200,200), paint)
    }
    private fun loadBitmaps(){
        imgResIDs.forEach {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }
    private fun drawChessboard(canvas: Canvas?){
        for (i in 0..7) {

            for (j in 0..7) {
                paint.color = if ((i + j) % 2 == 0)Color.LTGRAY
                else Color.BLACK
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