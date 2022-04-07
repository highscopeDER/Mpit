package com.example.mpit.view.mainActivity

import android.content.Intent
import android.util.Log
import com.example.mpit.model.FirebaseUsers
import com.example.mpit.model.User
import com.example.mpit.view.profileActivity.ProfilePageActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import moxy.InjectViewState
import moxy.MvpPresenter
import kotlin.coroutines.coroutineContext

@InjectViewState
class MainPagePresenter : MvpPresenter<MainPageInterface>() {

    private lateinit var mAuth: FirebaseAuth
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val usr = getCurrentUser()
        if (usr != null){
            viewState.updateUser(usr)
        }
        else {
            provideLoginIntent()
        }
    }

    private fun getCurrentUser(): FirebaseUser? {
        mAuth = FirebaseAuth.getInstance()
        return mAuth.currentUser
    }

    fun userSignOut(){
        mAuth.signOut()
        provideLoginIntent()
    }

    fun openProfilePage(){
        val loggedUser = getCurrentUser()
        if (loggedUser != null){
            viewState.launchProfile(ProfilePageActivity::class.java, loggedUser)
        }
    }

    private fun provideLoginIntent(){
        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build(),)
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        viewState.launchUserLogin(signInIntent)
    }

}