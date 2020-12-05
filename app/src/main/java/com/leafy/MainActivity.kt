package com.leafy

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.leafy.ui.login.LoginAndRegisterFragment
import com.leafy.ui.root.RootFragment

class MainActivity : AppCompatActivity() {

    private val uesrViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun setFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        tr.add(R.id.fragment_container, RootFragment())
        tr.commitAllowingStateLoss()
    }
}