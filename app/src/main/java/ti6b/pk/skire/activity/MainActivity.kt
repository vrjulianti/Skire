package ti6b.pk.skire.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import ti6b.pk.skire.*
import ti6b.pk.skire.R
import ti6b.pk.skire.adapter.JobsAdapter
import ti6b.pk.skire.adapter.TipsAdapter
import ti6b.pk.skire.model.Jobs
import ti6b.pk.skire.model.Tips

class MainActivity : AppCompatActivity(), TipsView {

    lateinit var dbRef : DatabaseReference
    private lateinit var tipsPresenter: TipsPresenter
    private lateinit var tipsAdapter: TipsAdapter
    val datas: MutableList<Tips> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jobs: ArrayList<Jobs> = ArrayList()

        tipsPresenter = TipsPresenter(this, this)
        tipsPresenter.onFetchingData()

        initTipsData()

        dbRef = FirebaseDatabase.getInstance().getReference("Jobs")
        rv_recBased.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                println("InfoKotlin: ${p0.message}")
            }

            override fun onDataChange(p0: DataSnapshot) {
                jobs.clear()
                for(dataSnapshot in p0.children){
                    val getValue = dataSnapshot.getValue(Jobs::class.java)
                    getValue?.id = dataSnapshot.key

                    getValue?.let { jobs.add(it) }
                }

                rv_recBased.adapter = JobsAdapter(applicationContext, jobs) {
                    startActivity(intentFor<JobDesc>("id" to it.id, "image" to it.image, "title" to it.title,
                        "total_rec" to it.total_rec, "salary" to it.salary, "duration" to it.duration, "description" to it.description))
                }
            }
        })


    }

    @SuppressLint("WrongConstant")
    fun initTipsData(){
        tipsAdapter = TipsAdapter(this, datas)
        rv_tips.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv_tips.adapter = tipsAdapter
    }

    override fun getData(data: List<Tips>?) {
        data?.let { datas.addAll(it) }
    }
}
