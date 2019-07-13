package ti6b.pk.skire.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_rec_based.view.*
import kotlinx.android.synthetic.main.layout_list_pekerjaan.view.*
import ti6b.pk.skire.R
import ti6b.pk.skire.model.Jobs

class SearchAdapter(
    private val context: Context,
    private val jobs: ArrayList<Jobs>,
    private val listener: (Jobs) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchAdapter.ViewHolder =
        SearchAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_pekerjaan, p0, false))

    override fun getItemCount(): Int = jobs.size

    override fun onBindViewHolder(p0: SearchAdapter.ViewHolder, p1: Int) =
        p0.bindNews(jobs[p1], context, listener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindNews(jobs: Jobs, context: Context, listener: (Jobs) -> Unit){

            jobs.image.let {
                Glide.with(context).asBitmap().load(it).into(itemView.image_ref)
            }

            itemView.textTitlePekerjaan.text = jobs.title
            itemView.textRangeGaji.text = jobs.salary
            itemView.textDurasi.text = jobs.duration


            itemView.view_pekerjaan.setOnClickListener {
                listener(jobs)
            }
        }
    }

}