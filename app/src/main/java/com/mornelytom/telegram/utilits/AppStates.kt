package com.mornelytom.telegram.utilits

enum class AppStates(val state: String) {
    ONLINE("online"),
    OFFLINE("last seen recently"),
    TYPING("typing...");

    companion object {
        fun updateState(appStates: AppStates) {
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(USER_STATE)
                .setValue(appStates.state)
                .addOnSuccessListener { USER.state = appStates.state }
                .addOnFailureListener { showToast(it.message.toString()) }
        }
    }
}