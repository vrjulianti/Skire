package ti6b.pk.skire.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_recruiter.view.*
import ti6b.pk.skire.R
import ti6b.pk.skire.model.Company

class CompanyAdapter(
    private val company: ArrayList<Company>,
    private val listener: (Company) -> Unit
) : RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.item_recruiter,
                p0,
                false
            )
        )

    override fun getItemCount(): Int = company.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) =
        p0.bindNews(company[p1], listener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindNews(company: Company, listener: (Company) -> Unit){

            /*company.logo.let {
                Glide.with(context).asBitmap().load(it).into(itemView.image_company)
            }**/

            itemView.tv_companyName.text = company.name


            /*itemView.cv_recruiter.setOnClickListener {
                listener(company)
            }**/
        }
    }
}