package ti6b.pk.skire.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_training.*
import org.jetbrains.anko.support.v4.longToast
import ti6b.pk.skire.R
import ti6b.pk.skire.adapter.MaterialAdapter
import ti6b.pk.skire.model.Material

class TrainingFragment : Fragment() {

    lateinit var dbRef : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val material: ArrayList<Material> = ArrayList()

        dbRef = FirebaseDatabase.getInstance().getReference("Submateri")
        rv_material.layoutManager = LinearLayoutManager(activity)

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

                rv_material.adapter = MaterialAdapter(material) {
                    longToast("rv berhasil")

                }
            }
        })
    }


}