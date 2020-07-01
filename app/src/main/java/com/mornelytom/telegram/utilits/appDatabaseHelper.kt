package com.mornelytom.telegram.utilits

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mornelytom.telegram.models.User

lateinit var AUTH: FirebaseAuth
lateinit var UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference

lateinit var USER: User

const val NODE_USERS = "users"
const val NODE_USERNAMES = "usernames"
const val FOLDER_PROFILE_IMAGE = "profile_image"

const val USER_ID = "id"
const val USER_PHONE = "phone"
const val USER_NAME = "username"
const val USER_FULLNAME = "fullname"
const val USER_BIO = "bio"
const val USER_PHOTO_URL = "photoUrl"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    USER = User()
    UID = AUTH.currentUser?.uid.toString()
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
}