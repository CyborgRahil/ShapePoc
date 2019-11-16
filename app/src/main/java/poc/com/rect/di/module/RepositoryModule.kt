package poc.com.rect.di.module

import poc.com.rect.data.RectDataRepository
import poc.com.rect.data.RectDataRepositoryImpl
import poc.com.rect.data.local.RectDao

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRectDataRepository(rectDao: RectDao): RectDataRepository {
        return RectDataRepositoryImpl(rectDao)
    }

}
