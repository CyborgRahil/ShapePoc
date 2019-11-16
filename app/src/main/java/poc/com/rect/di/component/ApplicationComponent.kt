package poc.com.rect.di.component

import poc.com.rect.RectApp
import poc.com.rect.data.RectDataRepository
import poc.com.rect.data.local.RectDao
import poc.com.rect.di.ApplicationScope
import poc.com.rect.di.module.*
import dagger.Component
import poc.com.rect.utility.SchedulerProvider
import javax.inject.Singleton


@Singleton
@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class,RepositoryModule::class, RoomModule::class))
interface ApplicationComponent {

    fun rectApp(): RectApp

    fun rectDataRepository(): RectDataRepository

    fun rectDao(): RectDao

    fun schedularProvider():SchedulerProvider

}
