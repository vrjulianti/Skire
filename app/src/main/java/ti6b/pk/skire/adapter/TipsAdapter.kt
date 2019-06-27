package ti6b.pk.skire.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_tips.view.*
import ti6b.pk.skire.R
import ti6b.pk.skire.model.Tips

class TipsAdapter (private val context: Context, private val data: List<Tips>) :
    RecyclerView.Adapter<TipsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_tips,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bindItems(context, data[position], position)
    }

    class ViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bindItems(context: Context, tips: Tips, position: Int) {

            tips.image.let {
                Glide.with(context).asBitmap().load(it).into(containerView.image_news)
            }

            containerView.tv_tipsTitle.text = tips.title
            containerView.tv_tipsSource.text = tips.source

            containerView.setOnClickListener {

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tips.tipsUrl))
                context.startActivity(intent)

            }

        }
    }
}