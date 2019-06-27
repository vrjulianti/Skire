package ti6b.pk.skire.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_job_desc.*
import ti6b.pk.skire.adapter.PagerAdapter
import ti6b.pk.skire.R


class JobDesc : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_desc)

        val fragmentAdapter = PagerAdapter(supportFragmentManager)
        container.adapter = fragmentAdapter

        tablayout.setupWithViewPager(container)

    }

}
