package poc.com.rect.addRect.view

import poc.com.rect.addRect.customviews.RectEdgeBall
import poc.com.rect.base.BasePresenter
import poc.com.rect.base.BaseView
import poc.com.rect.data.local.RectEntity
import java.util.*

interface AddRectContract {

    interface AddRectView: BaseView<Presenter> {
        fun showSuccessMessage(message:String)
        fun saveRectData()
    }

    interface Presenter : BasePresenter<AddRectView> {

        fun addRectData(rectEntity: RectEntity)
        fun createRectData(rectData: ArrayList<RectEdgeBall>) : RectEntity

    }
}