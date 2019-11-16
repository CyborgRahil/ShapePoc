package poc.com.rect.rectList

import org.junit.Test
import poc.com.rect.testUtility.TestUtililty
import poc.com.rect.rectList.domain.usecase.RectListUseCaseImpl
import poc.com.rect.stub.RectDataRepositoryStub

class RectListUseCaseTest {
    @Test
    fun `execute should return rect list`() {
        val rectEntity = TestUtililty.createEntity()
        val rectDataRepository = RectDataRepositoryStub(rectEntity)
        val useCase = RectListUseCaseImpl(rectDataRepository)

        val result = useCase.execute().test()

        result.assertNoErrors()
        result
                .assertValue { it[0].rectId == rectEntity.rectId }
                .assertValue { it[0].coordinate == rectEntity.coordinate }

    }
}