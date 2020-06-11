package com.mornelytom.telegram.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.mornelytom.telegram.R
import com.mornelytom.telegram.databinding.ActivityAuthorizationBinding
import com.mornelytom.telegram.ui.fragments.EnterPhoneFragment

class AuthorizationActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAuthorizationBinding
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.authToolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.auth_title_phone)
        supportFragmentManager.beginTransaction()
            .add(R.id.authDataContainer, EnterPhoneFragment())
            .commit()
    }
}