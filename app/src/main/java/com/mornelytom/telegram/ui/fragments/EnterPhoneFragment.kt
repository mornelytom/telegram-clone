package com.mornelytom.telegram.ui.fragments

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mornelytom.telegram.R
import kotlinx.android.synthetic.main.fragment_enter_phone.*

class EnterPhoneFragment : Fragment(R.layout.fragment_enter_phone) {
    override fun onStart() {
        super.onStart()
        auth_btn_next.setOnClickListener { sentCode() }
    }

    private fun sentCode() {
        if (auth_input_phone.text.toString().isEmpty()) {
            Toast.makeText(activity, getString(R.string.auth_toast_enter_phone), Toast.LENGTH_SHORT)
                .show()
        } else {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.authDataContainer, EnterCodeFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}