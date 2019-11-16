package poc.com.rect.rectList.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import poc.com.rect.R
import poc.com.rect.base.BaseActivity
import javax.inject.Inject
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_rect_list.*
import poc.com.rect.addRect.view.AddRectActivity
import poc.com.rect.data.local.RectEntity

class RectListActivity : BaseActivity(), RectListContract.RectListView {

    lateinit var mRectAdapter: RectListAdapter

    @Inject
    lateinit var mRectListPresenter: RectListPresenter

    /**
     * Show error message while getting any error while frtching data from database
     */
    override fun showError(errorMessage: String) {
        val snackbar = Snackbar.make(findViewById(R.id.coordinator_layout), errorMessage, Snackbar.LENGTH_SHORT)
        snackbar.show()
    }

    /**
     *  Populate Recycler view after getting element from DB
     *  @list It contains list of Rect entity
     */
    override fun populateRecyclerView(list: List<RectEntity>) {
            mRectAdapter = RectListAdapter(list)
        rect_recycler_view.adapter = mRectAdapter
        }

    /**
     * Handle fab item click listener
     */
    override fun fabItemClick() {
         var intent = Intent(this,AddRectActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBaliActivityComponent().inject(this)
        setContentView(R.layout.activity_rect_list)
        initRecyclerView()
        fabItemClickListener();

    }

    private fun fabItemClickListener() {
        add_rect_fab.setOnClickListener { fabItemClick() }
    }

    private fun initRecyclerView() {
        rect_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }

    override fun onStop() {
        super.onStop()
        mRectListPresenter.dropView()
    }

    override fun onStart() {
        super.onStart()
        mRectListPresenter.takeView(this)

    }

    override fun onResume() {
        super.onResume()
        mRectListPresenter.getRectList()
    }

}