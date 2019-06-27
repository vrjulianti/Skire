package ti6b.pk.skire.activity

import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_onboarding.*
import org.jetbrains.anko.startActivity
import ti6b.pk.skire.R

class OnboardingActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.buttonEmail -> startActivity<RegisterActivity>()
            R.id.buttonSignin -> startActivity<LoginActivity>()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        buttonEmail.setOnClickListener(this)
        buttonSignin.setOnClickListener(this)

    }
}
