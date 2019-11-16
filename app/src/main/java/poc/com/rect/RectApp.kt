package poc.com.rect

import android.app.Application
import android.util.Log
import poc.com.rect.base.BaseActivity
import poc.com.rect.di.component.ActivityComponent
import poc.com.rect.di.component.ApplicationComponent
import poc.com.rect.di.module.ActivityModule
import poc.com.rect.di.module.ApplicationModule
import poc.com.rect.di.module.RoomModule
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import poc.com.rect.di.component.DaggerActivityComponent
import poc.com.rect.di.component.DaggerApplicationComponent

/**
 * Created by global on 28/4/18.
 */


 class RectApp : Application() {


    private var applicationComponent: ApplicationComponent? = null


    override fun onCreate() {
        super.onCreate()
        init()
        applicationContext
        setupRxErrorHandler()
    }

    private fun init() {
        initDagger()
    }

    /**
     * Initialize dagger for Application Module and create ApplicationComponent instance
     *
     */
    private fun initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .roomModule(RoomModule(this))
                .build()
    }

    /**
     * Create instance of Activity component.
     *
     */
    fun createActivityComponent(activity: BaseActivity): ActivityComponent {
        return DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(ActivityModule(activity))
                .build()
    }

    fun getDiComponent(): ApplicationComponent? {
        return applicationComponent
    }



    /**
     * Handle unknown Exception of Rx Java
     *
     */
    fun setupRxErrorHandler() {
        if (!BuildConfig.DEBUG) {
            RxJavaPlugins.setErrorHandler { throwable ->
                if (throwable is OnErrorNotImplementedException) {
                    Thread.getDefaultUncaughtExceptionHandler()
                            .uncaughtException(Thread.currentThread(), throwable)
                } else if (throwable is UndeliverableException) {
                    Log.e("exception", throwable.toString())
                } else {
                    Log.e("exception", throwable.toString())
                }
                Log.e("exception", throwable.toString())
            }
        }
    }
}

