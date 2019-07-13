package ti6b.pk.skire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*
import org.jetbrains.anko.toast
import ti6b.pk.skire.activity.LoginActivity

class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v){
            btn_send -> {
                val email = edtEmail.text.toString()
                when{
                    email.isEmpty() -> edtEmail.error = "Wajib Diisi"
                    else -> {
                        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    edtEmail.setText("")
                                    toast("Link reset password sent to your email")
                                }
                            }
                            .addOnFailureListener {
                                toast("Email not registered")
                            }
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btn_send.setOnClickListener(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener{
            startActivity(Intent(this@ForgotPasswordActivity, LoginActivity::class.java))
            finish()
        }
    }
}