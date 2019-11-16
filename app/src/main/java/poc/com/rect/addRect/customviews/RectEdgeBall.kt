package poc.com.rect.addRect.customviews

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.util.Log

class RectEdgeBall(internal var mContext: Context, resourceId: Int, internal var point: Point) {

    var bitmap: Bitmap
        internal set
    var id: Int = 0
        internal set

    val widthOfBall: Int
        get() = bitmap.width

    val heightOfBall: Int
        get() = bitmap.height

    var x: Int
        get() = point.x
        set(x) {
            point.x = x
        }

    var y: Int
        get() = point.y
        set(y) {
            point.y = y
        }

    init {
        if (count <4){
            this.id = count++
        }
        bitmap = BitmapFactory.decodeResource(mContext.resources,
                resourceId)
    }

    companion object {
        internal var count = 0
    }
}

