package ti6b.pk.skire.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_onboarding.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import ti6b.pk.skire.R

class OnboardingActivity : AppCompatActivity() {

    /*override fun onClick(v: View) {
        /*val i = v.id
        when (i) {
            R.id.buttonEmail -> startActivity<RegisterActivity>()
            R.id.buttonSignin -> startActivity<LoginActivity>()
        }**/
    }**/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        buttonEmail.setOnClickListener {
            startActivity(intentFor<RegisterActivity>())
            finish()
        }

        buttonSignin.setOnClickListener{
            startActivity(intentFor<LoginActivity>())
            finish()
        }

    }
}
