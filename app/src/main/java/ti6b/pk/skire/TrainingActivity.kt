package ti6b.pk.skire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_job_desc.*
import kotlinx.android.synthetic.main.activity_training.*
import kotlinx.android.synthetic.main.activity_training.container
import kotlinx.android.synthetic.main.activity_training.tablayout
import org.jetbrains.anko.intentFor
import ti6b.pk.skire.adapter.FragTrainingAdapter

class TrainingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        val fragmentAdapter = FragTrainingAdapter(supportFragmentManager)
        container.adapter = fragmentAdapter

        tablayout.setupWithViewPager(container)




    }
}
