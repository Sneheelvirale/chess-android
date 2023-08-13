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

    override fun onDraw(canvas: Canvas?) {
        val paint = Paint()
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
        val whiteQueenBitmap: Bitmap
        whiteQueenBitmap = BitmapFactory.decodeResource(resources, R.drawable.white_queen)
        canvas?.drawBitmap(whiteQueenBitmap, null, Rect(0,0,100,100), paint)
    }
}