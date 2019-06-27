package ti6b.pk.skire.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_job_desc.*
import ti6b.pk.skire.adapter.PagerAdapter
import ti6b.pk.skire.R
import ti6b.pk.skire.model.Jobs


class JobDesc : AppCompatActivity() {

    private lateinit var jobs: Jobs


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_desc)

        val fragmentAdapter = PagerAdapter(supportFragmentManager)
        container.adapter = fragmentAdapter

        tablayout.setupWithViewPager(container)
        jobs = Jobs(
            intent.getStringExtra("image"))


    }

}
