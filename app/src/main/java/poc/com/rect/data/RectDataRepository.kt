package poc.com.rect.data

import io.reactivex.Completable
import io.reactivex.Flowable
import poc.com.rect.data.local.RectEntity

interface RectDataRepository {

    fun getRectangleList(): Flowable<List<RectEntity>>

    fun addRectangleCoordinate(item: RectEntity) : Completable
}

