package poc.com.rect.rectList.view

import poc.com.rect.base.BasePresenter
import poc.com.rect.base.BaseView
import poc.com.rect.data.local.RectEntity

interface RectListContract {

    interface RectListView : BaseView<Presenter> {
        fun populateRecyclerView(list:List<RectEntity>)
        fun fabItemClick()
    }

    interface Presenter : BasePresenter<RectListView> {
        fun getRectList()
    }

}