package com.gap.bookquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gap.bookquiz.finfishFragment.FinishFragment
import com.gap.bookquiz.startFragment.StartFragment

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val currentFragment = fragmentManager.findFragmentById(R.id.main_fragment_container_view)

        if (currentFragment is FinishFragment) {
            val transaction = fragmentManager.beginTransaction()
            val startFragment = StartFragment()
            transaction.replace(R.id.main_fragment_container_view, startFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            return
        }


        super.onBackPressed()

    }
}