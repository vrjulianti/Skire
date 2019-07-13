package ti6b.pk.skire.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import ti6b.pk.skire.activity.MainActivity
import ti6b.pk.skire.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mAuth : FirebaseAuth

    override fun onClick(v: View?) {
        when(v){
            buttonLogin -> loginUser()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        buttonLogin.setOnClickListener(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener{
            startActivity(Intent(this@LoginActivity, OnboardingActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {

    }

    private fun loginUser() {
        val email = edtEmailLogin.text.toString()
        val password = edtPasswordLogin.text.toString()

        when {
            email.isEmpty() -> edtEmailLogin.error = "Wajib diisi"
            password.isEmpty() -> edtPasswordLogin.error = "Wajib diisi"

            else -> {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            mAuth.currentUser
                            startActivity(intentFor<MainActivity>())
                            longToast("Login Berhasil")
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            longToast("Login Gagal")
                        }

                    }

            }


        }

    }
}
