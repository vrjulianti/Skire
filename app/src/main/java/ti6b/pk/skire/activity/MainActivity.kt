package ti6b.pk.skire.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toolbar
import ti6b.pk.skire.R
import ti6b.pk.skire.fragment.HomeFragment
import ti6b.pk.skire.fragment.PelatihanFragment
import ti6b.pk.skire.fragment.ProfileFragment

class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadHomeFragment(savedInstanceState)

                }
                R.id.navigation_pelatihan -> {
                    loadTrainingFragment(savedInstanceState)

                }
                R.id.navigation_profil -> {
                    loadProfileFragment(savedInstanceState)

                }
            }

            true
        }
        bottomNavigation.selectedItemId = R.id.navigation_home

    }

    private fun loadHomeFragment(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.navigationContainer, HomeFragment(), HomeFragment::class.java.simpleName)
                .commitAllowingStateLoss()
        }
    }

    private fun loadProfileFragment(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.navigationContainer, ProfileFragment(), ProfileFragment::class.java.simpleName)
                .commitAllowingStateLoss()
        }
    }

    private fun loadTrainingFragment(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.navigationContainer, PelatihanFragment(), PelatihanFragment::class.java.simpleName)
                .commitAllowingStateLoss()
        }
    }



}


