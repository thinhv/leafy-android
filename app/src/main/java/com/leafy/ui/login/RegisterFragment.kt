package com.leafy.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.leafy.R
import com.leafy.UserViewModel
import com.leafy.repository.Resource
import com.leafy.repository.Status
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_register.setOnClickListener {
            val email = et_email.text.toString()
            val username = et_username.text.toString()
            val password = et_password.text.toString()

            var errorMessages: String? = null

            if (username.isEmpty()) {
                errorMessages = "Username is needed!"
            }

            if (email.isEmpty()) {
                errorMessages = "Email is needed!"
            }

            if (password.isEmpty()) {
                errorMessages = "Password is needed!"
            }

            if (errorMessages != null) {
                Snackbar.make(view, errorMessages, Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            userViewModel.register(username = username, email = email, password = password).observe(viewLifecycleOwner, Observer {
                when (it.status) {

                    Status.ERROR -> {
                       btn_register.isEnabled = true
                       Snackbar.make(view, it.message ?: "", Snackbar.LENGTH_LONG).show()
                    }

                    Status.LOADING -> {
                        btn_register.isEnabled = false
                    }

                    Status.SUCCESS -> {
                        btn_register.isEnabled = true
                        Snackbar.make(view, "Register successfully!", Snackbar.LENGTH_LONG).show()
                    }
                }
            })
        }
    }
}