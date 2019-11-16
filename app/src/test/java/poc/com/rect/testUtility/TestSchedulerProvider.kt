package poc.com.rect.testUtility

import io.reactivex.schedulers.TestScheduler
import poc.com.rect.utility.SchedulerProvider

class TestSchedulerProvider() : SchedulerProvider {

    val testScheduler: TestScheduler = TestScheduler()

    override fun uiScheduler() = testScheduler
    override fun ioScheduler() = testScheduler
}