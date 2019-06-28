package ti6b.pk.skire.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_recruiter.*
import org.jetbrains.anko.support.v4.intentFor
import ti6b.pk.skire.DetailTrainingActivity
import ti6b.pk.skire.R
import ti6b.pk.skire.adapter.CompanyAdapter
import ti6b.pk.skire.model.Company

class RecruiterFragment : Fragment() {

    lateinit var dbRef : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recruiter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val company: ArrayList<Company> = ArrayList()

        dbRef = FirebaseDatabase.getInstance().getReference("Company")
        rv_recruiter.layoutManager = LinearLayoutManager(activity)

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println("InfoKotlin: ${p0.message}")
            }

            override fun onDataChange(p0: DataSnapshot) {
                company.clear()
                for(dataSnapshot in p0.children){
                    val getValue = dataSnapshot.getValue(Company::class.java)
                    getValue?.id = dataSnapshot.key

                    getValue?.let { company.add(it) }
                }

                rv_recruiter.adapter = CompanyAdapter(company) {
                    startActivity(intentFor<DetailTrainingActivity>("title" to it.name))

                }
            }
        })
    }


}