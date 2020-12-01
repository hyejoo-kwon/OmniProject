package com.example.omniproject.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.omniproject.R
import com.mobsandgeeks.saripaar.annotation.NotEmpty


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SignUpConfirmFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SignUpConfirmFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpConfirmFragment : Fragment() {
    @BindView(R.id.etConfirmCode)
    @NotEmpty
    var etConfirmCode: EditText? = null
    private var mListener: OnFragmentInteractionListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =
            inflater.inflate(R.layout.fragment_sign_up_confirm, container, false)
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

    interface OnFragmentInteractionListener {
        fun confirmSignUp(responseCode: String?)
    }

    @OnClick(R.id.btnSignUpConfirm)
    fun doConfirm() {
        mListener!!.confirmSignUp(etConfirmCode!!.text.toString())
    }

    companion object {
        private val TAG = SignUpConfirmFragment::class.java.simpleName

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpConfirmFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): SignUpConfirmFragment {
            return SignUpConfirmFragment()
        }
    }
}
