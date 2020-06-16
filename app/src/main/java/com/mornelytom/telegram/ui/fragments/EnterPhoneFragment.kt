package com.mornelytom.telegram.ui.fragments

import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.mornelytom.telegram.MainActivity
import com.mornelytom.telegram.R
import com.mornelytom.telegram.activities.AuthorizationActivity
import com.mornelytom.telegram.utilits.AUTH
import com.mornelytom.telegram.utilits.replaceActivity
import com.mornelytom.telegram.utilits.replaceFragment
import com.mornelytom.telegram.utilits.showToast
import kotlinx.android.synthetic.main.fragment_enter_phone.*
import java.util.concurrent.TimeUnit

class EnterPhoneFragment : Fragment(R.layout.fragment_enter_phone) {

    private lateinit var mPhone: String
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onStart() {
        super.onStart()
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        (activity as AuthorizationActivity).replaceActivity(MainActivity())
                    } else {
                        showToast(it.exception.toString())
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
               replaceFragment(EnterCodeFragment(mPhone, id))
            }

        }
        auth_btn_next.setOnClickListener { sentCode() }
    }

    private fun sentCode() {
        if (auth_input_phone.text.toString().isEmpty()) {
            showToast(getString(R.string.auth_toast_enter_phone))
        } else {
            authUser()
        }
    }

    private fun authUser() {
        mPhone = auth_input_phone.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhone, 120, TimeUnit.SECONDS,
            activity as AuthorizationActivity,
            mCallback
        )
    }
}