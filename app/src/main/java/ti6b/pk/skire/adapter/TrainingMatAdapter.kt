package ti6b.pk.skire.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_material.view.*
import kotlinx.android.synthetic.main.item_training.view.*
import ti6b.pk.skire.R
import ti6b.pk.skire.model.Material

class TrainingMatAdapter(
    private val material: ArrayList<Material>,
    private val listener: (Material) -> Unit
) : RecyclerView.Adapter<TrainingMatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.item_training,
                p0,
                false
            )
        )

    override fun getItemCount(): Int = material.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) =
        p0.bindNews(material[p1], listener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindNews(material: Material, listener: (Material) -> Unit){

            itemView.tv_itemTitle.text = material.title

            itemView.list_training.setOnClickListener {
                listener(material)
            }
        }
    }
}