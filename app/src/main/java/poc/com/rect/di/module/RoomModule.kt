package poc.com.rect.di.module

import dagger.Provides
import javax.inject.Singleton
import android.arch.persistence.room.Room
import android.app.Application
import poc.com.rect.data.local.RectDao
import poc.com.rect.data.local.RectRoomDb
import dagger.Module



@Module
class RoomModule(mApplication: Application) {

    private val roomDbDatabase: RectRoomDb

    init {
        roomDbDatabase = Room.databaseBuilder(mApplication, RectRoomDb::class.java, "RectApp.db").build()
    }

    @Singleton
    @Provides
    fun providesRectRoomDb(): RectRoomDb {
        return roomDbDatabase
    }

    @Singleton
    @Provides
    fun providesRectDao(roomDbDataBase: RectRoomDb): RectDao {
        return roomDbDatabase.getRectangleDao()
    }


}