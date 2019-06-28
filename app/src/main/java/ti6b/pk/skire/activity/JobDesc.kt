package ti6b.pk.skire.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_job_desc.*
import org.jetbrains.anko.startActivity
import ti6b.pk.skire.R
import ti6b.pk.skire.TrainingActivity
import ti6b.pk.skire.adapter.PagerAdapter


class JobDesc : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v){
            buttonRecruit -> startActivity<TrainingActivity>()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_desc)

        val fragmentAdapter = PagerAdapter(supportFragmentManager)
        container.adapter = fragmentAdapter

        buttonRecruit.setOnClickListener(this)

        tablayout.setupWithViewPager(container)

    }

}
