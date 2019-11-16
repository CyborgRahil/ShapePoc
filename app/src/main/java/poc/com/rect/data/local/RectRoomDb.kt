package poc.com.rect.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(RectEntity::class), version = 1,exportSchema = false)
abstract class RectRoomDb : RoomDatabase() {
    abstract fun getRectangleDao() : RectDao
}