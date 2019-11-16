package poc.com.rect.stub

import io.reactivex.Completable
import io.reactivex.Flowable
import poc.com.rect.data.RectDataRepository
import poc.com.rect.data.local.RectEntity

class RectDataRepositoryStub (
        private val rectEntity: RectEntity) : RectDataRepository{
    override fun getRectangleList(): Flowable<List<RectEntity>> {
        return Flowable.just(listOf(rectEntity))
    }

    override fun addRectangleCoordinate(item: RectEntity): Completable {
        return Completable.complete()
    }
}
