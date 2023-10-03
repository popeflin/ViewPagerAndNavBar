package com.dewabrata.fragmenttutorial.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dewabrata.fragmenttutorial.uifragment.FragmentPage1
import com.dewabrata.fragmenttutorial.uifragment.FragmentPage2
import com.dewabrata.fragmenttutorial.uifragment.FragmentPage3

class ViewPagerAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
       return 3
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
            0 -> FragmentPage1()
            1 -> FragmentPage2()
            2 -> FragmentPage3()
            else -> FragmentPage1()
        }
    }
}