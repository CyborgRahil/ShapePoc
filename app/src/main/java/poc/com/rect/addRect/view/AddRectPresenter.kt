package poc.com.rect.addRect.view

import io.reactivex.disposables.CompositeDisposable
import poc.com.rect.addRect.customviews.RectEdgeBall
import poc.com.rect.addRect.domain.usecase.AddRectUseCase
import poc.com.rect.data.local.RectEntity
import poc.com.rect.utility.SchedulerProvider
import java.lang.StringBuilder
import java.util.*
import javax.inject.Inject

class AddRectPresenter
@Inject
constructor(private val addRectUseCase: AddRectUseCase, private val schedulerProvider: SchedulerProvider) : AddRectContract.Presenter {

    private var mView: AddRectContract.AddRectView? = null
    private val mDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * Add rect data into DB
     */
    override fun addRectData(rectEntity: RectEntity) {
            mDisposable.add(addRectUseCase.execute(rectEntity)
                    .subscribeOn(schedulerProvider.ioScheduler())
                    .observeOn(schedulerProvider.uiScheduler())
                    .subscribe({
                        mView!!.showSuccessMessage("Your information is added successfully")
                    }, { mView!!.showError("Please try again") }

                    )
            )
    }

    override fun createRectData(rectData: ArrayList<RectEdgeBall>): RectEntity {
        var rectEntity = RectEntity()
        rectEntity.coordinate = makeCoordinateString(rectData)
        return rectEntity
    }

    private fun makeCoordinateString(rectData: ArrayList<RectEdgeBall>): String? {
        rectData.sortBy { rectEdgeBall -> rectEdgeBall.y }
        var result = StringBuilder()
        result.append("Coordinates:{")
        for (i in rectData.indices) {
            val edges = rectData.get(i)
            result.append("(X" + (i + 1) + ":" + edges.x + ",")
            result.append("Y" + (i + 1) + ":" + edges.y + "),")
        }
        result.append("}")
        return result.toString()
    }


    override fun takeView(view: AddRectContract.AddRectView) {
        mView = view
    }

    override fun dropView() {
        mDisposable.dispose()
        mView = null
    }

}