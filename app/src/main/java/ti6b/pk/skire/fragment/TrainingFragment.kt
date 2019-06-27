package ti6b.pk.skire.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_desc.*
import kotlinx.android.synthetic.main.fragment_training.*
import org.jetbrains.anko.support.v4.longToast
import ti6b.pk.skire.R
import ti6b.pk.skire.adapter.MaterialAdapter
import ti6b.pk.skire.model.Jobs
import ti6b.pk.skire.model.Material

class TrainingFragment : Fragment() {

    private lateinit var material: Material

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        material = Material()

        material.title = activity?.intent?.getStringExtra("title")

        tv_jobTitle.text = material.title

    }

}