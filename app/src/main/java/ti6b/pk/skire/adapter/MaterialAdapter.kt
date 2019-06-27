package ti6b.pk.skire.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_material.view.*
import kotlinx.android.synthetic.main.item_rec_based.view.*
import ti6b.pk.skire.R
import ti6b.pk.skire.model.Jobs
import ti6b.pk.skire.model.Material

class MaterialAdapter(
    private val context: Context,
    private val material: ArrayList<Material>,
    private val listener: (Material) -> Unit
) : RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_material,
                p0,
                false
            )
        )

    override fun getItemCount(): Int = material.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) =
        p0.bindNews(material[p1], context, listener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindNews(material: Material, context: Context, listener: (Material) -> Unit){

            itemView.tv_materialTitle.text = material.title

            itemView.cv_jobs.setOnClickListener {
                listener(material)
            }
        }
    }
}