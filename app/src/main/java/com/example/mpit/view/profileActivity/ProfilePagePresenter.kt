package com.example.mpit.view.profileActivity

import android.widget.Toast
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ProfilePagePresenter : MvpPresenter<ProfilePageInterface>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.message("Profile Activity")
    }

}
