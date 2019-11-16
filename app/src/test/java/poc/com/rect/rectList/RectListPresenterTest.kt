package poc.com.rect.rectList

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import poc.com.rect.testUtility.TestUtililty
import poc.com.rect.data.RectDataRepository
import poc.com.rect.data.local.RectEntity
import poc.com.rect.rectList.domain.usecase.RectListUseCaseImpl
import poc.com.rect.rectList.domain.usecase.RectListUseCase
import poc.com.rect.rectList.view.RectListContract
import poc.com.rect.rectList.view.RectListPresenter
import poc.com.rect.testUtility.TestSchedulerProvider

class RectListPresenterTest {

    lateinit var rectListUseCase: RectListUseCase

    lateinit var testSchedulerProvider: TestSchedulerProvider
    @Mock
    lateinit var rectDataRepository: RectDataRepository

    @Mock
    lateinit var rectListView: RectListContract.RectListView

    lateinit var rectListPresenter: RectListPresenter

    var rectEntity: RectEntity = TestUtililty.createEntity()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSchedulerProvider = TestSchedulerProvider()
        rectListUseCase = RectListUseCaseImpl(rectDataRepository)
        rectListPresenter = RectListPresenter(rectListUseCase,testSchedulerProvider)
    }

    @Test
    fun `get rect list from db`() {
        Mockito.`when`(rectDataRepository.getRectangleList()).thenReturn(Flowable.just(listOf(rectEntity)))
        rectListPresenter.takeView(rectListView)
        rectListPresenter.getRectList()
        testSchedulerProvider.testScheduler.triggerActions()
        verify(rectListView).populateRecyclerView(any())
    }

    @Test
    fun `get error while getting list from db`() {
        Mockito.`when`(rectDataRepository.getRectangleList()).thenReturn(Flowable.error(Throwable()))
        rectListPresenter.takeView(rectListView)
        rectListPresenter.getRectList()
        testSchedulerProvider.testScheduler.triggerActions()
        verify(rectListView).showError(any())
    }

    @Test
    fun `get blank list while getting list from db`() {
        Mockito.`when`(rectDataRepository.getRectangleList()).thenReturn(Flowable.just(emptyList()))
        rectListPresenter.takeView(rectListView)
        rectListPresenter.getRectList()
        testSchedulerProvider.testScheduler.triggerActions()
        verify(rectListView).showError(any())
    }

}