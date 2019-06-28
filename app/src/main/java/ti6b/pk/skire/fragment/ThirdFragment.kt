package ti6b.pk.skire.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.rv_trainingBased
import kotlinx.android.synthetic.main.fragment_third.*
import org.jetbrains.anko.support.v4.longToast
import ti6b.pk.skire.R
import ti6b.pk.skire.adapter.TrainingMatAdapter
import ti6b.pk.skire.model.Material

class ThirdFragment : Fragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v){
            buttonRecruit -> showAlert()
        }

    }

    lateinit var dbRef : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    private fun showAlert(){
        buttonRecruit.visibility = View.GONE
        tv_succeed.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val material: ArrayList<Material> = ArrayList()

        buttonRecruit.setOnClickListener(this)

        dbRef = FirebaseDatabase.getInstance().getReference("Submateri")
        rv_trainingBased.layoutManager = LinearLayoutManager(activity)

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println("InfoKotlin: ${p0.message}")
            }

            override fun onDataChange(p0: DataSnapshot) {
                material.clear()
                for(dataSnapshot in p0.children){
                    val getValue = dataSnapshot.getValue(Material::class.java)
                    getValue?.id = dataSnapshot.key

                    getValue?.let { material.add(it) }
                }

                rv_trainingBased.adapter = TrainingMatAdapter(material) {
                    longToast("rv berhasil")

                }
            }
        })
    }


}