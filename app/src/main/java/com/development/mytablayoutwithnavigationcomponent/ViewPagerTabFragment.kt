package com.development.mytablayoutwithnavigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntegerRes
import androidx.core.content.ContextCompat
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_view_pager_tab.*

class ViewPagerTabFragment : Fragment() {

    private val iconList = arrayListOf<Int>(
        R.drawable.ic_add,
        R.drawable.ic_hamburger,
        R.drawable.ic_add
    )

    private val tabTitleList = arrayListOf<String>(
        "One",
        "Two",
        "Three"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewpager.adapter = adapter

        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            tab.setIcon(iconList[position])
            tab.setText(tabTitleList[position])
        /*when (position) {
                0 -> {
                    tab.text = "ONE"
                }
                1 -> {
                    tab.text = "TWO"
                }
                2 -> {
                    tab.text = "THREE"
                }
            }*/
        }.attach()

        //set the tab icons
      /*  tablayout.getTabAt(0)?.setIcon(iconList[0]);
        tablayout.getTabAt(1)?.setIcon(iconList[1]);
        tablayout.getTabAt(2)?.setIcon(iconList[2]);*/

        //set the badge
        val badgeDrawable: BadgeDrawable = tablayout.getTabAt(1)!!.getOrCreateBadge()
        badgeDrawable.isVisible = true
        badgeDrawable.number = 5
        badgeDrawable.backgroundColor = ContextCompat.getColor(requireActivity(),R.color.black)
    }
}