package poc.com.rect.addRect.view

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_rectangle.*
import poc.com.rect.R
import poc.com.rect.base.BaseActivity
import poc.com.rect.data.local.RectEntity
import javax.inject.Inject

class AddRectActivity : BaseActivity(),AddRectContract.AddRectView {


    @Inject
    lateinit var mAddRectPresenter: AddRectPresenter

    /***
     * Show success message if rect entity is successfully add into DB
     */
    override fun showSuccessMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        finish()
    }

    /**
     * Show error if found any issue while inserting data into DB
     */
    override fun showError(errorMessage: String) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
    }

    override fun saveRectData() {
       val rectEntity =  mAddRectPresenter.createRectData(rectDrawView.getEdgesOfRectangle())
        mAddRectPresenter.addRectData(rectEntity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBaliActivityComponent().inject(this)
        setContentView(R.layout.activity_add_rectangle)
        btn_save.setOnClickListener{ saveRectData() }
    }



    override fun onStop() {
        super.onStop()
        mAddRectPresenter.dropView()
    }

    override fun onStart() {
        super.onStart()
        mAddRectPresenter.takeView(this)

    }}