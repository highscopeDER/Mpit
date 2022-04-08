package com.example.mpit.view.mainActivity.activity

import android.content.Intent
import android.os.Parcelable
import com.example.mpit.model.User
import com.example.mpit.view.profileActivity.ProfilePageActivity
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import com.google.firebase.auth.FirebaseUser
import moxy.MvpAppCompatActivity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface MainPageInterface : MvpView {
    fun launchUserLogin(intent: Intent)
    fun updateUser(user: FirebaseUser)
    fun launchProfile(page: Class<ProfilePageActivity>, user: Parcelable)
}