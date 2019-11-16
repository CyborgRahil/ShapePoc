package poc.com.rect.data

import poc.com.rect.data.local.RectDao

import io.reactivex.Completable
import io.reactivex.Flowable
import poc.com.rect.data.local.RectEntity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RectDataRepositoryImpl
@Inject
constructor(private val rectDao: RectDao) : RectDataRepository {
    override fun addRectangleCoordinate(item: RectEntity): Completable {
        var result = Completable.fromCallable({ rectDao.addRectangleCoordinate(item) })
        return result
    }

    override fun getRectangleList(): Flowable<List<RectEntity>> {
        return rectDao.getRectangleList()
    }
}