package com.mornelytom.telegram.models

class User(
    val id: String = "",
    var username: String = "",
    var fullname: String = "",
    var bio: String = "",
    var status: String = "",
    var phone: String = "",
    var photoUrl: String = ""
)