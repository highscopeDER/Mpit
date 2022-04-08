package com.example.mpit.view.mainActivity.activity

import android.content.Context
import android.content.Intent
import com.example.mpit.R
import com.example.mpit.model.User
import com.example.mpit.view.profileActivity.ProfilePageActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import moxy.InjectViewState
import moxy.MvpPresenter

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

    fun createIntent(context: Context, response: IdpResponse?): Intent {
        var user: User? = null
        val loggedUser = getCurrentUser()
        if (loggedUser != null){
            val mail = loggedUser.email
            val name = loggedUser.displayName
            if (mail != null && name != null){
                user = User(mail, name, "Волонтер", 18)
            }
        }

        return Intent().setClass(context, ProfilePageActivity::class.java)
            .putExtra("idpResponse", response)
            .putExtra("name", user)
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
            val mail = loggedUser.email
            val name = loggedUser.displayName
            if (mail != null && name != null){
                viewState.launchProfile(ProfilePageActivity::class.java, User(mail, name, "Волонтер", 18)
                )
            }
        }
    }

    private fun provideLoginIntent(){
        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build(),)
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setLogo(R.drawable.logo)
            .build()
        viewState.launchUserLogin(signInIntent)
    }
}