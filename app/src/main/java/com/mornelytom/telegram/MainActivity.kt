package com.mornelytom.telegram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.mornelytom.telegram.activities.AuthorizationActivity
import com.mornelytom.telegram.databinding.ActivityMainBinding
import com.mornelytom.telegram.ui.fragments.ChatFragment
import com.mornelytom.telegram.ui.objects.AppDrawer
import com.mornelytom.telegram.utilits.replaceActivity
import com.mornelytom.telegram.utilits.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if (true) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatFragment())
        } else {
            replaceActivity(AuthorizationActivity())
        }

    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)
    }
}