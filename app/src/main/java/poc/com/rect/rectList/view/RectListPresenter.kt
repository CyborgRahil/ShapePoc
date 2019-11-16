package poc.com.rect.rectList.view

import io.reactivex.disposables.CompositeDisposable
import poc.com.rect.data.local.RectEntity
import poc.com.rect.rectList.domain.usecase.RectListUseCase
import poc.com.rect.utility.SchedulerProvider
import javax.inject.Inject

class RectListPresenter
@Inject
constructor(private val rectListUseCase: RectListUseCase,private val schedulerProvider:SchedulerProvider) :
RectListContract.Presenter {

    var mView: RectListContract.RectListView?=null
    var mDisposable: CompositeDisposable ?= null

    override fun takeView(view: RectListContract.RectListView) {
           mView = view
           mDisposable = CompositeDisposable()
    }
    override fun dropView() {
        mDisposable!!.dispose()
        mView=null
    }

    /**
     * get list of rect data from DB
     */
    override fun getRectList() {
      mDisposable!!.add(rectListUseCase.execute()
              .subscribeOn(schedulerProvider.ioScheduler())
              .observeOn(schedulerProvider.uiScheduler())
              .subscribe({  if (it.size>0) {
                  mView!!.populateRecyclerView(it)
              } else {
                  mView!!.showError("Please add a rectangle.")
              }
              }, {mView!!.showError("Please try again.")})
      )
    }


}