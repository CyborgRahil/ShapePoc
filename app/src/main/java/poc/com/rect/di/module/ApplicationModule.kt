package poc.com.rect.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import poc.com.rect.RectApp

import dagger.Module
import dagger.Provides
import poc.com.rect.utility.AppSchedulerProvider
import poc.com.rect.utility.SchedulerProvider
import javax.inject.Singleton



@Module
class ApplicationModule(private val mainApplication: RectApp) {

    @Provides
    @Singleton
    internal fun provideMainApplication(): RectApp {
        return mainApplication
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferences(mainApplication: RectApp): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(mainApplication)
    }

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return mainApplication
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider() : SchedulerProvider = AppSchedulerProvider()

}
