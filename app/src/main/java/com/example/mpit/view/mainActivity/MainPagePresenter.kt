package com.example.mpit.view.mainActivity

import android.util.Log
import com.example.mpit.model.FirebaseUsers
import com.example.mpit.model.User
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPagePresenter : MvpPresenter<MainPageInterface>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build(),)
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        viewState.launchUserLogin(signInIntent)
    }
}