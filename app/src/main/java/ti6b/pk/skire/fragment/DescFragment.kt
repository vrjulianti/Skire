package ti6b.pk.skire.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_desc.*
import ti6b.pk.skire.R
import ti6b.pk.skire.model.Jobs

class DescFragment : Fragment() {

    private lateinit var jobs: Jobs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_desc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        jobs = Jobs()

        jobs.id = activity?.intent?.getStringExtra("id")
        jobs.title = activity?.intent?.getStringExtra("title")
        jobs.total_rec = activity?.intent?.getStringExtra("total_rec")
        jobs.salary = activity?.intent?.getStringExtra("salary")
        jobs.duration = activity?.intent?.getStringExtra("duration")
        jobs.description = activity?.intent?.getStringExtra("description")

        tv_jobTitle.text = jobs.title
        tv_jobRecruiter.text = jobs.total_rec
        tv_jobSalary.text = jobs.salary
        tv_jobDuration.text = jobs.duration
        tv_jobDesc.text = jobs.description

    }


}