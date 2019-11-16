package poc.com.rect.utility

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun uiScheduler() : Scheduler
    fun ioScheduler() : Scheduler
}