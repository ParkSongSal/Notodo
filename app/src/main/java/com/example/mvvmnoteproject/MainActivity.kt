package com.example.mvvmnoteproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmnoteproject.constants.Constants
import com.example.mvvmnoteproject.ui.fragment.FirstFragment
import com.example.mvvmnoteproject.ui.fragment.SecondFragment
import com.example.mvvmnoteproject.ui.fragment.ThirdFragment
import com.example.mvvmnoteproject.databinding.ActivityMainBinding
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mBottom_navigation = findViewById<AnimatedBottomBar>(R.id.bottomNavigationView)

        mBottom_navigation.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                i: Int,
                tab: AnimatedBottomBar.Tab?,
                i1: Int,
                tab1: AnimatedBottomBar.Tab
            ) {
                when (tab1.id) {
                    R.id.tab_home -> callFragment(Constants.FRAGMENT1)
                    R.id.tab_nang -> callFragment(Constants.FRAGMENT2)
                    R.id.tab_setting -> callFragment(Constants.FRAGMENT3)
                }
            }

            override fun onTabReselected(
                i: Int,
                tab: AnimatedBottomBar.Tab
            ) {
            }
        })
        callFragment(Constants.FRAGMENT1)

    }

    // 선택한 Fragment 호출
    private fun callFragment(frament_no: Int) {

        // 프래그먼트 사용을 위해
        val transaction =
            supportFragmentManager.beginTransaction()
        when (frament_no) {
            1 -> {
                // 'Home' 호출
                val fragment1 = FirstFragment()
                transaction.replace(R.id.mainFrame, fragment1)
                transaction.commit()
            }
            2 -> {
                // '1등판매점' 호출
                val fragment2 = SecondFragment()
                transaction.replace(R.id.mainFrame, fragment2)
                transaction.commit()
            }
            3 -> {
                // '1등판매점' 호출
                val fragment3 = ThirdFragment()
                transaction.replace(R.id.mainFrame, fragment3)
                transaction.commit()
            }
        }
    }
}