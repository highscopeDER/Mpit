package com.example.mpit.view.profileActivity

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface ProfilePageInterface : MvpView {
    fun message(msg: String)
}
