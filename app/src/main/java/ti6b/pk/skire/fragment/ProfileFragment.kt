package ti6b.pk.skire.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ti6b.pk.skire.R

class ProfileFragment : Fragment() {
    //    Firebase Reference
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    //    UI elements
    private var tvFullName: TextView? = null
    //    private var tvAddress: TextView? = null
    private var tvPhoneNumber: TextView? = null
    private var tvEmail: TextView? = null
    private var tvPendidikan: TextView? = null
    //    private var tvEmailVerified: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialise(view)

        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)

        tvEmail!!.text = mUser.email

        mUserReference.addValueEventListener(object :
            ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                tvFullName!!.text = p0.child("name").value as String
//                tvAddress!!.text = p0.child("address").value as String
                tvPhoneNumber!!.text = p0.child("no_hp").value as String
                tvEmail!!.text = p0.child("email").value as String
                tvPendidikan!!.text = p0.child("pendidikan").value as String
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })
    }

    private fun initialise(view: View) {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        tvFullName = view.findViewById<View>(R.id.textNamaProfil) as TextView
//        tvAddress = findViewById<View>(R.id.textAlamatProfil) as TextView
        tvPhoneNumber = view.findViewById<View>(R.id.textTeleponProfil) as TextView
        tvEmail = view.findViewById<View>(R.id.textEmailProfil) as TextView
        tvPendidikan = view.findViewById<View>(R.id.textPendidikanProfil) as TextView

//        tvEmailVerifiied = findViewById<View>(R.id.tv_email_verifiied) as TextView
    }

}