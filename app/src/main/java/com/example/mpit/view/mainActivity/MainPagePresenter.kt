package com.example.mpit.view.mainActivity

import com.example.mpit.R
import com.example.mpit.model.User
import com.example.mpit.view.profileActivity.ProfilePageActivity
import com.firebase.ui.auth.AuthUI
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
                viewState.launchProfile(ProfilePageActivity::class.java, User(mail, name, "Волонтер")
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