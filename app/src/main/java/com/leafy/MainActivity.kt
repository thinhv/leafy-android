package com.leafy

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.leafy.ui.login.LoginAndRegisterFragment
import com.leafy.ui.root.RootFragment

class MainActivity : AppCompatActivity() {

    private lateinit var uesrViewModel: UserViewModel
    private var currentFragment: Fragment? = null
    private var isLoggedIn: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uesrViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        uesrViewModel.isLoggedIn.observe(this, {
            if (isLoggedIn != null && isLoggedIn == it) {  return@observe }
            isLoggedIn = it
            if (it) {
                setFragment(RootFragment())
            } else {
                setFragment(LoginAndRegisterFragment())
            }
        })
    }

    private fun setFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        currentFragment?.let { tr.remove(it) }
        currentFragment = fragment
        tr.add(R.id.fragment_container, fragment)
        tr.commitAllowingStateLoss()
    }
}