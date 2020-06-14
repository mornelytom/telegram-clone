package com.mornelytom.telegram.ui.fragments

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mornelytom.telegram.R
import com.mornelytom.telegram.utilits.replaceFragment
import com.mornelytom.telegram.utilits.showToast
import kotlinx.android.synthetic.main.fragment_enter_phone.*

class EnterPhoneFragment : Fragment(R.layout.fragment_enter_phone) {
    override fun onStart() {
        super.onStart()
        auth_btn_next.setOnClickListener { sentCode() }
    }

    private fun sentCode() {
        if (auth_input_phone.text.toString().isEmpty()) {
            showToast(getString(R.string.auth_toast_enter_phone))
        } else {
            replaceFragment(EnterCodeFragment())
        }
    }
}