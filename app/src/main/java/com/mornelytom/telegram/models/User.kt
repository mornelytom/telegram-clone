package com.mornelytom.telegram.models

class User(
    val id: String = "",
    var username: String = "",
    var fullname: String = "",
    var bio: String = "",
    var state: String = "",
    var phone: String = "",
    var photoUrl: String = "empty"
)