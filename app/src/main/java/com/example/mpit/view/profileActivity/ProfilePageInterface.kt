package com.example.mpit.view.profileActivity

import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface ProfilePageInterface : MvpView {
    fun message(msg: String)
    fun setProfileImage(file: Uri)

}

