package poc.com.rect.rectList.domain.usecase

import poc.com.rect.data.RectDataRepository
import poc.com.rect.data.local.RectEntity
import io.reactivex.Flowable
import javax.inject.Inject

interface RectListUseCase {

    fun execute() : Flowable<List<RectEntity>>
}


class  RectListUseCaseImpl
  @Inject
  constructor(private val repository: RectDataRepository) : RectListUseCase {
    override fun execute(): Flowable<List<RectEntity>> {
        return repository.getRectangleList()

    }
}