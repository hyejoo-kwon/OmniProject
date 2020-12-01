package com.example.omniproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.omniproject.fragments.SignUpConfirmFragment
import com.example.omniproject.fragments.SignUpFragment


class SignUpActivity : FragmentActivity(),
    SignUpFragment.OnFragmentInteractionListener,
    SignUpConfirmFragment.OnFragmentInteractionListener {
    private var userName: String? = null
    private var password: String? = null
    private var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setSignUpFragment()
        context = this
    }

    private fun setSignUpFragment() {
        val signUpFragment = SignUpFragment()
        val transaction =
            supportFragmentManager.beginTransaction()
        transaction.replace(R.id.signup_layout, signUpFragment)
        transaction.commit()
    }

    private fun setSignUpConfirmFragment() {
        val signUpConfirmFragment =
            SignUpConfirmFragment()
        val transaction =
            supportFragmentManager.beginTransaction()
        transaction.replace(R.id.signup_layout, signUpConfirmFragment)
        transaction.commit()
    }

    override fun signUp(email: String?, password: String?) {
        userName = email
        this.password = password

        // Add code here
    }

    override fun confirmSignUp(code: String?) {
        // Add code here
    }

    private fun _signIn(username: String, password: String) {
        // Add code here
    }

    companion object {
        private val TAG = SignUpActivity::class.java.simpleName
    }
}