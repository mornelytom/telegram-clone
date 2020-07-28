package com.mornelytom.telegram.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.firebase.storage.StorageReference
import com.mornelytom.telegram.R
import com.mornelytom.telegram.activities.AuthorizationActivity
import com.mornelytom.telegram.utilits.*
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        settings_bio.text = USER.bio
        settings_full_name.text = USER.fullname
        settings_phone.text = USER.phone
        settings_username.text = USER.username
        settings_status.text = USER.status
        settings_btn_change_username.setOnClickListener { replaceFragment(ChangeUsernameFragment()) }
        settings_btn_change_bio.setOnClickListener { replaceFragment(ChangeBioFragment()) }
        settings_change_photo.setOnClickListener { changeUserPhoto() }
        settings_user_photo.downloadAndSetImage(USER.photoUrl)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (APP_ACTIVITY).replaceActivity(AuthorizationActivity())
            }
            R.id.settings_menu_change_name -> replaceFragment(ChangeNameFragment())
        }
        return true
    }

    private fun changeUserPhoto() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(APP_ACTIVITY, this)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK && data != null
        ) {
            // selected image
            val uri = CropImage.getActivityResult(data).uri
            // upload image to storage
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE).child(UID)
            putImageStorage(uri, path) {
                getUrlFromStorage(path) {
                    putUrlToDatabase(it) {
                        settings_user_photo.downloadAndSetImage(it)
                        showToast(getString(R.string.toast_data_update))
                        USER.photoUrl = it
                        APP_ACTIVITY.mAppDrawer.updateHeader()
                    }
                }
            }
        }
    }
}