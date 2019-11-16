package poc.com.rect.data.local

import android.arch.persistence.room.*
import io.reactivex.Flowable


@Dao
interface RectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRectangleCoordinate(item: RectEntity)

    @Query("SELECT * FROM RectTable ORDER BY rectId DESC")
    fun getRectangleList(): Flowable<List<RectEntity>>

}