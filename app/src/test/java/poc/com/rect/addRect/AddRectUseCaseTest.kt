package poc.com.rect.addRect

import org.junit.Test
import poc.com.rect.testUtility.TestUtililty
import poc.com.rect.addRect.domain.usecase.AddRectUseCaseImpl
import poc.com.rect.stub.RectDataRepositoryStub

class AddRectUseCaseTest {

    @Test
    fun `execute should return Completable`() {
        val rectEntity = TestUtililty.createEntity()
        val rectDataRepository = RectDataRepositoryStub(rectEntity)
        val useCase = AddRectUseCaseImpl(rectDataRepository)

        val result = useCase.execute(rectEntity).test()

        result.assertNoErrors()
        result.assertComplete()
    }
}