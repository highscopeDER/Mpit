package com.example.mpit.view.mainActivity

import android.content.Intent
import com.example.mpit.model.User
import com.google.firebase.auth.FirebaseUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface MainPageInterface : MvpView {
    fun launchUserLogin(intent: Intent)
    fun updateUser(user: FirebaseUser)
}