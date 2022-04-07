package com.example.mpit.view.mainActivity

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import android.widget.Toast
import com.example.mpit.R
import com.example.mpit.model.User
import com.example.mpit.view.profileActivity.ProfilePageActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainPageActivity : MvpAppCompatActivity(), MainPageInterface {

    @InjectPresenter
    lateinit var presenter: MainPagePresenter

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        val response = res.idpResponse
        if (res.resultCode == RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                goToProfile(user)
                Toast.makeText(
                    this,
                    "name: ${user.displayName}, mail: ${user.email}",
                    Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(
                this,
                "none",
                Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun launchUserLogin(intent: Intent) {
        signInLauncher.launch(intent)
    }

    override fun updateUser(user: FirebaseUser) {
        Log.v("TAG", "${user.email}, ${user.displayName}")
    }

    private fun goToProfile(loggedUser: FirebaseUser){
        val intent = Intent(applicationContext,  ProfilePageActivity::class.java)
        val mail = loggedUser.email
        val name = loggedUser.displayName
        if (mail != null && name != null){
            val parcel = User(
                mail,
                name
            )
            intent.apply {
                putExtra("user", parcel)
            }
        }
        startActivity(intent)
    }

}