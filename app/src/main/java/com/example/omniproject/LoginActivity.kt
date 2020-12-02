package com.example.omniproject


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.amazonaws.AmazonServiceException
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.results.SignInResult
import com.amazonaws.mobile.client.results.SignInState
import com.example.omniproject.CommonUtil.makeToast
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.Validator.ValidationListener
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password


class LoginActivity : AppCompatActivity(), ValidationListener {
    var validator: Validator? = null

    @BindView(R.id.etEmail)
    @NotEmpty
    @Email
    var etEmail: EditText? = null



    @BindView(R.id.etPassword)
    @Password(
            min = 8,
            scheme = Password.Scheme.ANY
    )
    var etPassword: EditText? = null
    private var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("hello", "LoginActivity생성")
        ButterKnife.bind(this)
        validator = Validator(this)
        validator!!.setValidationListener(this)
        context = this
    }

    override fun onValidationSucceeded() {
        Log.d("hello", "onValidationSucceeded stat")
        _signIn(etEmail!!.text.toString(), etPassword!!.text.toString())
    }

    override fun onValidationFailed(errors: List<ValidationError>) {
        for (error in errors) {
            val view = error.view
            val message = error.getCollatedErrorMessage(this)

            // Display error messages ;)
            if (view is EditText) {
                view.error = message
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun _signIn(userName: String, password: String) {
        Log.d("hello", "_singIn 함수 실행")
        // Add code here
        AWSMobileClient.getInstance().signIn(userName, password, null, object : Callback<SignInResult> {
            override fun onResult(signInResult: SignInResult) {
                runOnUiThread {
                    Log.d("hello", "Sign-in callback state: " + signInResult.signInState)
                    when (signInResult.signInState) {
                        SignInState.DONE -> {
                            makeToast(context, "Sign-in done.")
                            CommonAction.openMain(context!!)
                        }
                        SignInState.SMS_MFA -> makeToast(context, "Please confirm sign-in with SMS.")
                        SignInState.NEW_PASSWORD_REQUIRED -> makeToast(context, "Please confirm sign-in with new password.")
                        else -> makeToast(context, "Unsupported sign-in confirmation: " + signInResult.signInState)
                    }
                }
            }

            override fun onError(e: Exception) {
                Log.e("hello", "Sign-in error", e)
                runOnUiThread { if (e is AmazonServiceException) makeToast(context, (e as AmazonServiceException).errorMessage) }
            }
        })
    }

    fun doLogin(view: View?) {
        Log.d("hello", "doLogin function start")
        validator!!.validate()
    }

    companion object {
        private val TAG = LoginActivity::class.java.simpleName
    }
}