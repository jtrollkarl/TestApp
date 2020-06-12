package com.example.testapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.R
import com.example.testapp.doTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSearchFragmentIfStackEmpty()
    }

    private fun showSearchFragmentIfStackEmpty() {
        supportFragmentManager.takeIf { it.fragments.isEmpty() }
            ?.doTransaction { replace(R.id.container, FragmentSearch()) }
    }
}
