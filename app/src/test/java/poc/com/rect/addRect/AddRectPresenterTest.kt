package poc.com.rect.addRect

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

import poc.com.rect.testUtility.TestUtililty
import poc.com.rect.addRect.domain.usecase.AddRectUseCase
import poc.com.rect.addRect.domain.usecase.AddRectUseCaseImpl
import poc.com.rect.addRect.view.AddRectContract
import poc.com.rect.addRect.view.AddRectPresenter
import poc.com.rect.data.RectDataRepository
import poc.com.rect.data.local.RectEntity
import poc.com.rect.testUtility.TestSchedulerProvider

class AddRectPresenterTest {
    @Mock
    lateinit var rectDataRepository: RectDataRepository

    @Mock
    lateinit var mAddRectView: AddRectContract.AddRectView

    lateinit var mAddRectPresenter: AddRectPresenter

    lateinit var addRectUseCase: AddRectUseCase

    var rectEntity:RectEntity= TestUtililty.createEntity()
    lateinit var testSchedulerProvider: TestSchedulerProvider

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        testSchedulerProvider = TestSchedulerProvider()
        addRectUseCase = AddRectUseCaseImpl(rectDataRepository)

        mAddRectPresenter = AddRectPresenter(addRectUseCase,testSchedulerProvider)
    }

    @Test
    fun `add Rect data into db`() {
        Mockito.`when`(rectDataRepository.addRectangleCoordinate(rectEntity)).thenReturn(Completable.complete())
        mAddRectPresenter.takeView(mAddRectView)
        mAddRectPresenter.addRectData(rectEntity)
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mAddRectView).showSuccessMessage(any())

    }

    @Test
    fun `get error while add Rect data into db`() {
        Mockito.`when`(rectDataRepository.addRectangleCoordinate(rectEntity)).thenReturn(Completable.error(Throwable()))
        mAddRectPresenter.takeView(mAddRectView)
        mAddRectPresenter.addRectData(rectEntity)
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mAddRectView).showError(any())

    }

}