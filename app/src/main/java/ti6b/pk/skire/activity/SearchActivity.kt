package ti6b.pk.skire.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_list_pekerjaan.view.*
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_search.*
import ti6b.pk.skire.R.layout.layout_list_pekerjaan
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import ti6b.pk.skire.model.Jobs
import ti6b.pk.skire.R
import ti6b.pk.skire.adapter.SearchAdapter


class SearchActivity : AppCompatActivity() {

    lateinit var mSearchText : String
    lateinit var mRecyclerSearch : RecyclerView

    lateinit var mDatabase : DatabaseReference

    lateinit var adapter: SearchAdapter
    var jobList: MutableList<Jobs> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mSearchText = editSearch.text.toString()
        mRecyclerSearch = findViewById(R.id.recyclerSearch)

        mDatabase = FirebaseDatabase.getInstance().getReference("Jobs")
        adapter = SearchAdapter(this, jobList as ArrayList<Jobs>){

        }

        mRecyclerSearch.setHasFixedSize(true)
        mRecyclerSearch.layoutManager = LinearLayoutManager(this)
        mRecyclerSearch.adapter = adapter

        editSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                jobList.clear()

                val searchText = s.toString()
                loadFirebaseData(searchText)

            }

        })
    }

    private fun loadFirebaseData(searchText : String) {

        mDatabase.orderByChild("title").equalTo(searchText).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    val job = userSnapshot.getValue(Jobs::class.java)
                    jobList.add(job!!)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        })


    }

//    ViewHolder Class








}
