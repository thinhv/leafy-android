package com.leafy.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leafy.R
import kotlinx.android.synthetic.main.fragment_login_and_register.*

class LoginAndRegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_and_register, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AuthenticationPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(RegisterFragment())
        adapter.addFragment(LoginFragment())

        viewPager.adapter = adapter
    }
}