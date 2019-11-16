package poc.com.rect.di.component

import poc.com.rect.di.ActivityScope
import poc.com.rect.di.module.ActivityModule
import poc.com.rect.rectList.view.RectListActivity
import dagger.Component
import poc.com.rect.addRect.view.AddRectActivity

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))

interface ActivityComponent {

    fun inject(activity: RectListActivity)
    fun inject(activity: AddRectActivity)
}