package com.mornelytom.telegram.ui.fragments

import androidx.fragment.app.Fragment
import com.google.firebase.auth.PhoneAuthProvider
import com.mornelytom.telegram.MainActivity
import com.mornelytom.telegram.R
import com.mornelytom.telegram.activities.AuthorizationActivity
import com.mornelytom.telegram.utilits.*
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val phone: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        (activity as AuthorizationActivity).title = phone
        auth_input_code.addTextChangedListener(AppTextWatcher {
            val string = auth_input_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })
    }

    private fun enterCode() {
        val code = auth_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { signIn ->
            if (signIn.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dataMap = mutableMapOf<String, Any>()
                dataMap[USER_ID] = uid
                dataMap[USER_PHONE] = phone
                dataMap[USER_NAME] = uid

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dataMap)
                    .addOnCompleteListener { update ->
                        if (update.isSuccessful) {
                            (activity as AuthorizationActivity).replaceActivity(MainActivity())
                        } else {
                            showToast(update.exception?.message.toString())
                        }
                    }
            } else {
                showToast(signIn.exception?.message.toString())
            }
        }
    }
}