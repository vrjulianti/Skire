package ti6b.pk.skire.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ti6b.pk.skire.fragment.DescFragment
import ti6b.pk.skire.fragment.RecruiterFragment
import ti6b.pk.skire.fragment.TrainingFragment

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        DescFragment(),
        TrainingFragment(),
        RecruiterFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Deskripsi"
            1 -> "Pelatihan"
            else -> "Perekrut"
        }
    }
}