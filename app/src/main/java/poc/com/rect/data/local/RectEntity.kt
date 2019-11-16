package poc.com.rect.data.local

import android.arch.persistence.room.*


@Entity(tableName = "RectTable")
class RectEntity {
    @PrimaryKey(autoGenerate = true)
    var rectId:Int =0
    var coordinate: String ?= null
}

