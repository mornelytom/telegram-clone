package com.mornelytom.telegram.ui.fragments

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mornelytom.telegram.R
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        auth_input_code.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val string = auth_input_code.text.toString()
                if (string.length == 6) {
                    verifyCode()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    fun verifyCode() {
        Toast.makeText(activity, "Ok", Toast.LENGTH_SHORT).show()
    }
}