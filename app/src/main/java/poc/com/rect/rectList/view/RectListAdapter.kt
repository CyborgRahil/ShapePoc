package poc.com.rect.rectList.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_rect_list.view.*
import poc.com.rect.R
import poc.com.rect.data.local.RectEntity

class RectListAdapter(private val rectListData: List<RectEntity>) : RecyclerView.Adapter<RectListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RectListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_rect_list, parent, false)
        return RectListViewHolder(view)

    }

    override fun getItemCount(): Int {
        return rectListData.size

    }

    override fun onBindViewHolder(holder: RectListViewHolder?, position: Int) {
        holder?.bindForecast(rectListData.get(position))
    }

}

class RectListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * This method is use to bind data to adapter.
     *
     */
    fun bindForecast(rectData: RectEntity) {
        with(rectData) {
            itemView.tv_coordinate_of_rect.text = rectData.coordinate
        }
    }

} 