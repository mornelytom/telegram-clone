package com.mornelytom.telegram.ui.fragments

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mornelytom.telegram.R
import com.mornelytom.telegram.utilits.AppTextWatcher
import com.mornelytom.telegram.utilits.showToast
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        auth_input_code.addTextChangedListener(AppTextWatcher {
            val string = auth_input_code.text.toString()
            if (string.length == 6) {
                verifyCode()
            }
        })
    }

    fun verifyCode() {
        showToast("Ok")
    }
}