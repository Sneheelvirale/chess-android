package com.android.chesss

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.min

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private  val scaleFactor = 1.0f
    private  var originX = 20f
    private  var originY = 200f
    private  var cellSide = 130f
    private  val imgResIDs = setOf(
        R.drawable.bb,
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
    private  val bitmaps = mutableMapOf<Int, Bitmap>()
    private  val paint = Paint()
    private var movingPieceBitmap: Bitmap? = null
    private var movingPiece: ChessPiece? = null
    private var fromCol: Int= -1
    private var fromRow: Int= -1
    private var movingPieceX = -1f
    private var movingPieceY = -1f


    var chessDelegate: ChessDelegate? = null
    init {
        loadBitmaps()
    }


    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
            val chessBoardSide = min(width, height) * scaleFactor
            cellSide = chessBoardSide / 8f
            originX =  (width - chessBoardSide ) / 2f
            originY = (height - chessBoardSide ) / 2f
        drawChessboard(canvas)
        drawPieces(canvas)
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val smaller = min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(smaller, smaller)
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                fromCol = ((event.x - originX) / cellSide).toInt()
                fromRow = 7-((event.y - originY) / cellSide).toInt()
                movingPiece = chessDelegate?.pieceAt(fromCol, fromRow)
                movingPieceBitmap = bitmaps[movingPiece!!.resID]

                chessDelegate?.pieceAt(fromCol, fromRow)?.let {
                    movingPiece = it
                movingPieceBitmap = bitmaps[it.resID]
}
            }
            MotionEvent.ACTION_MOVE ->{
            movingPieceX = event.x
            movingPieceY = event.y
            invalidate()
            }
            MotionEvent.ACTION_UP -> {
                val  col = ((event.x - originX) / cellSide).toInt()
                val row = 7 - ((event.y - originY) / cellSide).toInt()
                Log.d(TAG, "from ($fromCol, $fromRow) to ($col, $row)")
                chessDelegate?.movePiece(fromCol,fromRow, col, row)
               movingPiece = null
                movingPieceBitmap = null

            }
        }
        return true
    }
    private fun drawPieces(canvas: Canvas) {


        for (row in 0..7) {
            for (col in 0..7) {
          //      if (row != fromRow || col != fromCol){
            //    chessDelegate?.pieceAt(col, row)?.let {
              //      drawPieceAt(canvas, col, row, it.resID)
                //}
                //}
                chessDelegate?.pieceAt(col, row)?.let {
                    if (it != movingPiece)
                    drawPieceAt(canvas, col, row, it.resID)
                }
            }
        }

        movingPieceBitmap?.let {
            canvas.drawBitmap(
                it,
                null,

                RectF(
                    movingPieceX - cellSide/2,
                    movingPieceY - cellSide/2,
                    movingPieceX + cellSide/2,
                    movingPieceY + cellSide/2
                ),
                paint
            )
        }


    }

    private fun drawPieceAt(canvas: Canvas, col: Int, row: Int, resID: Int) {
        val bitmap = bitmaps[resID]!!
        canvas.drawBitmap(
            bitmap,
            null,
            RectF(
                originX + col * cellSide,
                originY + (7 - row) * cellSide,
                originX + (col + 1) * cellSide,
                originY + ((7 - row) + 1) * cellSide
            ),
            paint
        )


    }

    private fun loadBitmaps() {
        imgResIDs.forEach {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }

    private fun drawChessboard(canvas: Canvas) {
        for (col in 0..7) {

            for (row in 0..7) {
                drawSquareAt(canvas, col, row, (col + row) % 2 == 1)

                }
                 }
            }
            private fun drawSquareAt(canvas: Canvas, col: Int, row: Int, isDark: Boolean) {
                paint.color = if (isDark)  Color.parseColor("#CC6600")
                else Color.parseColor("#FF9933")
                canvas?.drawRect(
                    originX + col * cellSide,
                    originY + row * cellSide,
                    originX + (col + 1) * cellSide,
                    originY + (row + 1) * cellSide,
                    paint
                )

            }
}


