package poc.com.rect.di.module

import poc.com.rect.base.BaseActivity
import poc.com.rect.data.RectDataRepository
import poc.com.rect.rectList.view.RectListPresenter

import dagger.Module
import dagger.Provides
import poc.com.rect.addRect.domain.usecase.AddRectUseCase
import poc.com.rect.addRect.domain.usecase.AddRectUseCaseImpl
import poc.com.rect.addRect.view.AddRectPresenter
import poc.com.rect.rectList.domain.usecase.RectListUseCase
import poc.com.rect.rectList.domain.usecase.RectListUseCaseImpl
import poc.com.rect.utility.SchedulerProvider


@Module
class ActivityModule(private val baseActivity: BaseActivity) {

    @Provides
    internal fun provideRectListUseCase(repository: RectDataRepository): RectListUseCase {
        return RectListUseCaseImpl(repository)
    }

    @Provides
    fun provideRectListPresenter(useCase: RectListUseCase, schedulerProvider: SchedulerProvider): RectListPresenter {
        return RectListPresenter(useCase,schedulerProvider)
    }


    @Provides
    internal fun provideAddRectUseCase(repository: RectDataRepository): AddRectUseCase {
        return AddRectUseCaseImpl(repository)
    }

    @Provides
    internal fun provideAddRectPresenter(useCase: AddRectUseCase, schedulerProvider: SchedulerProvider): AddRectPresenter {
        return AddRectPresenter(useCase,schedulerProvider)
    }
}