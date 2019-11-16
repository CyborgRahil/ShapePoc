package poc.com.rect.addRect.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import poc.com.rect.R
import java.util.*

class RectDrawView : View {

    var points = arrayOfNulls<Point>(4)

    /**
     * point1 and point 3 are of same group and same as point 2 and point4
     */
    var groupId = -1
    private val rectEdgeBalls = ArrayList<RectEdgeBall>()
    // array that holds the balls
    private var balID = 0
    // variable to know what ball is being dragged
    lateinit var paint: Paint
    lateinit var canvas: Canvas

    constructor(context: Context) : super(context) {
        paint = Paint()
        isFocusable = true // necessary for getting the touch events
        canvas = Canvas()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        paint = Paint()
        // setFocusable(true); // necessary for getting the touch events
        canvas = Canvas()
    }

    // the method that draws the balls
    override fun onDraw(canvas: Canvas) {
        if (points[3] == null)
        //point4 null when user did not touch and move on screen.
            return
        var left: Int
        var top: Int
        var right: Int
        var bottom: Int
        left = points[0]!!.x
        top = points[0]!!.y
        right = points[0]!!.x
        bottom = points[0]!!.y
        for (i in 1 until points.size) {
            left = if (left > points[i]!!.x) points[i]!!.x else left
            top = if (top > points[i]!!.y) points[i]!!.y else top
            right = if (right < points[i]!!.x) points[i]!!.x else right
            bottom = if (bottom < points[i]!!.y) points[i]!!.y else bottom
        }
        paint.isAntiAlias = true
        paint.isDither = true
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 5f

        //draw stroke
        paint.style = Paint.Style.STROKE
        paint.color = Color.parseColor("#AADB1255")
        paint.strokeWidth = 2f
        canvas.drawRect(
                (left + rectEdgeBalls.get(0).widthOfBall / 2).toFloat(),
                (top + rectEdgeBalls.get(0).widthOfBall / 2).toFloat(),
                (right + rectEdgeBalls.get(2).widthOfBall / 2).toFloat(),
                (bottom + rectEdgeBalls.get(2).widthOfBall / 2).toFloat(), paint)
        //fill the rectangle
        paint.style = Paint.Style.FILL
        paint.color = Color.parseColor("#55DB1255")
        paint.strokeWidth = 0f
        canvas.drawRect(
                (left + rectEdgeBalls.get(0).widthOfBall / 2).toFloat(),
                (top + rectEdgeBalls.get(0).widthOfBall / 2).toFloat(),
                (right + rectEdgeBalls.get(2).widthOfBall / 2).toFloat(),
                (bottom + rectEdgeBalls.get(2).widthOfBall / 2).toFloat(), paint)

        // draw the balls on the canvas
        paint.color = Color.BLUE
        paint.textSize = 18f
        paint.strokeWidth = 0f
        for (i in rectEdgeBalls.indices) {
            val edges = rectEdgeBalls.get(i)
            canvas.drawBitmap(edges.bitmap, edges.x.toFloat(), edges.y.toFloat(),
                    paint)

            canvas.drawText("" + (i + 1), edges.x.toFloat(), edges.y.toFloat(), paint)
        }
    }

    // events when touching the screen
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val eventaction = event.action

        val X = event.x.toInt()
        val Y = event.y.toInt()

        when (eventaction) {

            MotionEvent.ACTION_DOWN // touch down so check if the finger is on
            ->
                // a ball
                if (points[0] == null) {
                    //initialize rectangle.
                    points[0] = Point()
                    points[0]!!.x = X
                    points[0]!!.y = Y

                    points[1] = Point()
                    points[1]!!.x = X
                    points[1]!!.y = Y + 30

                    points[2] = Point()
                    points[2]!!.x = X + 30
                    points[2]!!.y = Y + 30

                    points[3] = Point()
                    points[3]!!.x = X + 30
                    points[3]!!.y = Y

                    balID = 2
                    groupId = 1
                    // declare each ball with the ColorBall class
                    for (pt in points) {
                        rectEdgeBalls.add(RectEdgeBall(context, R.drawable.gray_circle, pt!!))
                    }
                } else {
                    //resize rectangle
                    balID = -1
                    groupId = -1
                    for (i in rectEdgeBalls.indices.reversed()) {
                        val ball = rectEdgeBalls.get(i)
                        // check if inside the bounds of the ball (circle)
                        // get the center for the ball
                        val centerX = ball.x + ball.widthOfBall
                        val centerY = ball.y + ball.heightOfBall
                        paint.color = Color.CYAN
                        // calculate the radius from the touch to the center of the
                        // ball
                        val radCircle = Math
                                .sqrt(((centerX - X) * (centerX - X) + (centerY - Y) * (centerY - Y)).toDouble())

                        if (radCircle < ball.widthOfBall) {

                            balID = ball.id
                            if (balID == 1 || balID == 3) {
                                groupId = 2
                            } else {
                                groupId = 1
                            }
                            invalidate()
                            break
                        }
                        invalidate()
                    }
                }

            MotionEvent.ACTION_MOVE // touch drag with the ball
            ->
                if (balID > -1) {
                    // move the balls the same as the finger
                    rectEdgeBalls.get(balID).x = X
                    rectEdgeBalls.get(balID).y = Y

                    paint.color = Color.CYAN
                    if (groupId == 1) {
                        rectEdgeBalls.get(1).x = (rectEdgeBalls.get(0).x)
                        rectEdgeBalls.get(1).y = (rectEdgeBalls.get(2).y)
                        rectEdgeBalls.get(3).x = (rectEdgeBalls.get(2).x)
                        rectEdgeBalls.get(3).y = (rectEdgeBalls.get(0).y)
                    } else {
                        rectEdgeBalls.get(0).x = (rectEdgeBalls.get(1).x)
                        rectEdgeBalls.get(0).y = (rectEdgeBalls.get(3).y)
                        rectEdgeBalls.get(2).x = (rectEdgeBalls.get(3).x)
                        rectEdgeBalls.get(2).y = (rectEdgeBalls.get(1).y)
                    }

                    invalidate()
                }

            MotionEvent.ACTION_UP -> {
            }
        }// touch drop - just do things here after dropping
        // redraw the canvas
        invalidate()
        return true

    }

    fun  getEdgesOfRectangle(): ArrayList<RectEdgeBall>  {
       return  rectEdgeBalls
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        // View is now detached, and about to be destroyed
        rectEdgeBalls.clear()
        balID = -1
    }
}