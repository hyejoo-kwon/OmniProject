package com.example.omniproject.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.omniproject.R
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.Validator.ValidationListener
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password


class SignUpFragment : Fragment(), ValidationListener {
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

    @BindView(R.id.etPassword1)
    @ConfirmPassword
    var etPassword1: EditText? = null
    var validator: Validator
    private var mListener: OnFragmentInteractionListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_sign_up, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onValidationSucceeded() {
        mListener!!.signUp(etEmail!!.text.toString(), etPassword!!.text.toString())
    }

    override fun onValidationFailed(errors: List<ValidationError>) {
        for (error in errors) {
            val view = error.view
            val message = error.getCollatedErrorMessage(activity)

            // Display error messages ;)
            if (view is EditText) {
                view.error = message
            } else {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    interface OnFragmentInteractionListener {
        fun signUp(email: String?, password: String?)
    }

    @OnClick(R.id.btnRegistration)
    fun doSignUp() {
        Log.d(TAG, "doSignUp")
        validator.validate()
    }

    companion object {
        private val TAG = SignUpFragment::class.java.simpleName
        fun newInstance(param1: String?, param2: String?): SignUpFragment {
            return SignUpFragment()
        }
    }

    init {
        validator = Validator(this)
        validator.setValidationListener(this)
    }
}