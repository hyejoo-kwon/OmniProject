package com.example.omniproject


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
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
        ButterKnife.bind(this)
        validator = Validator(this)
        validator!!.setValidationListener(this)
        context = this
    }

    override fun onValidationSucceeded() {
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
        // Add code here
    }

    fun doLogin(view: View?) {
        validator!!.validate()
    }

    companion object {
        private val TAG = LoginActivity::class.java.simpleName
    }
}