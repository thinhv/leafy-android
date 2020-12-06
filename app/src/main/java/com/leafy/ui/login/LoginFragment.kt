package com.leafy.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.leafy.R
import com.leafy.UserViewModel
import com.leafy.repository.Status
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            val username = et_email.text.toString()
            val password = et_password.text.toString()
            var errorMessages: String? = null

            if (username.isEmpty()) {
                errorMessages = "Username or Email is needed"
            } else if (password.isEmpty()) {
                errorMessages = "Password is needed"
            }

            errorMessages?.let {
                Snackbar.make(view, errorMessages, Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            } ?: userViewModel.login(username = username, password = password).observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.LOADING -> { btn_login.isEnabled = false }
                    Status.SUCCESS -> { btn_login.isEnabled = true }
                    Status.ERROR -> {
                        btn_login.isEnabled = true
                        Snackbar.make(view, it.message ?: "", Snackbar.LENGTH_LONG).show()
                    }
                }
            })
        }
    }
}