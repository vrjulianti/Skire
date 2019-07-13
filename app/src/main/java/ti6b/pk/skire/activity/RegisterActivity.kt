package ti6b.pk.skire.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import ti6b.pk.skire.activity.MainActivity
import ti6b.pk.skire.R
import ti6b.pk.skire.model.Users

class RegisterActivity : AppCompatActivity() {


    //Firebase references
    lateinit var dbRef : DatabaseReference
    lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonRegister.setOnClickListener {
            createUser()
        }

        mAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //toolbar.navigationIcon = ContextCompat.getDrawable(this,R.drawable.ic_arrow_back_black_24dp)

        toolbar.setNavigationOnClickListener{
            startActivity(Intent(this@RegisterActivity, OnboardingActivity::class.java))
            finish()
        }

       /* if (mAuth.currentUser != null) {

            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
        }**/
    }

    private fun createUser() {

        val name = edtName.text.toString()
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()
        val no_hp = edtPhone.text.toString()
        val pendidikan = edtEducation.text.toString()

        when {
            name.isEmpty() -> edtName.error = "Wajib diisi"
            email.isEmpty() -> edtEmail.error = "Wajib diisi"
            password.isEmpty() -> edtPassword.error = "Wajib diisi"
            no_hp.isEmpty() -> edtPhone.error = "Wajib diisi"
            pendidikan.isEmpty() -> edtEducation.error = "Wajib diisi"
            password.length < 6 -> edtPassword.error = "Password min 6 karakter"

            else -> {
                val auth = FirebaseAuth.getInstance()

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            val user = Users(name, email, no_hp, pendidikan)

                            val newUser = dbRef.child(mAuth.currentUser!!.uid)
                            newUser.setValue(user).addOnCompleteListener(OnCompleteListener<Void> { task ->
                                if (task.isSuccessful) {
                                    progressBarRegist.visibility = View.GONE
                                    longToast("Registrasi Berhasil")
                                    startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                                    finish()

                                } else {
                                    longToast("Registrasi Gagal")
                                }
                            })
                        } else {
                            toast("Email already registered!")
                        }
                    }
                    .addOnFailureListener(this) {
                        println("InfoRegister: ${it.message}")
                    }
            }
        }

    }
}
