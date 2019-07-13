package ti6b.pk.skire.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.intentFor
import ti6b.pk.skire.TipsPresenter
import ti6b.pk.skire.TipsView
import ti6b.pk.skire.activity.JobDesc
import ti6b.pk.skire.adapter.JobsAdapter
import ti6b.pk.skire.adapter.TipsAdapter
import ti6b.pk.skire.model.Jobs
import ti6b.pk.skire.model.Tips
import ti6b.pk.skire.R
import ti6b.pk.skire.activity.SearchActivity

class HomeFragment : Fragment(), TipsView {

    lateinit var dbRef : DatabaseReference
    private lateinit var tipsPresenter: TipsPresenter
    private lateinit var tipsAdapter: TipsAdapter
    val datas: MutableList<Tips> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBar.setOnClickListener { startActivity(intentFor<SearchActivity>()) }

        val jobs: ArrayList<Jobs> = ArrayList()

        tipsPresenter = TipsPresenter(context!!, this)
        tipsPresenter.onFetchingData()

        initTipsData()

        dbRef = FirebaseDatabase.getInstance().getReference("Jobs")
        rv_recBased.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

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

                rv_recBased.adapter = JobsAdapter(context!!, jobs){
                    startActivity(intentFor<JobDesc>("id" to it.id, "image" to it.image, "title" to it.title,
                        "total_rec" to it.total_rec, "salary" to it.salary, "duration" to it.duration, "description" to it.description))

                }
            }
        })
    }

    @SuppressLint("WrongConstant")
    fun initTipsData(){
        tipsAdapter = TipsAdapter(context!!, datas)
        rv_tips.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        rv_tips.adapter = tipsAdapter
    }

    override fun getData(data: List<Tips>?) {
        data?.let { datas.addAll(it) }
    }

}