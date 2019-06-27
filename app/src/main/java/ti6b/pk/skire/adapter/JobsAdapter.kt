package ti6b.pk.skire.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_rec_based.view.*
import ti6b.pk.skire.model.Jobs
import ti6b.pk.skire.R

class JobsAdapter(
    private val context: Context,
    private val jobs: ArrayList<Jobs>,
    private val listener: (Jobs) -> Unit
) : RecyclerView.Adapter<JobsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_rec_based,
                p0,
                false
            )
        )

    override fun getItemCount(): Int = jobs.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) =
        p0.bindNews(jobs[p1], context, listener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindNews(jobs: Jobs, context: Context, listener: (Jobs) -> Unit){

            jobs.image.let {
                Glide.with(context).asBitmap().load(it).into(itemView.image_job)
            }

            itemView.tv_jobTitle.text = jobs.title
            itemView.tv_jobRecruiter.text = jobs.total_rec
            itemView.tv_jobSalary.text = jobs.salary
            itemView.tv_jobDuration.text = jobs.duration


            itemView.cv_jobs.setOnClickListener {
                listener(jobs)
            }
        }
    }
}