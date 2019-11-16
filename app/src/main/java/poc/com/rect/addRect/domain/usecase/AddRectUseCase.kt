package poc.com.rect.addRect.domain.usecase

import io.reactivex.Completable
import poc.com.rect.data.RectDataRepository
import poc.com.rect.data.local.RectEntity
import javax.inject.Inject

interface AddRectUseCase {

    fun execute(rectEntity: RectEntity) : Completable

}

class AddRectUseCaseImpl
@Inject
constructor(private val rectDataRepository:RectDataRepository) : AddRectUseCase {
    override fun execute(rectEntity: RectEntity): Completable {
       return rectDataRepository.addRectangleCoordinate(rectEntity)

    }
}