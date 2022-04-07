package com.example.mpit.view.mainActivity

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.TextView
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

const val USER = "user"

class MainPageActivity : MvpAppCompatActivity(), MainPageInterface {

    @InjectPresenter
    lateinit var presenter: MainPagePresenter
    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var profileButton: Button

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        val response = res.idpResponse
        if (res.resultCode == RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                presenter.openProfilePage()
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
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            presenter.userSignOut()
        }
        profileButton = findViewById(R.id.profile)
        profileButton.setOnClickListener {
            presenter.openProfilePage()
        }
    }

    override fun launchUserLogin(intent: Intent) {
        signInLauncher.launch(intent)
    }

    override fun updateUser(user: FirebaseUser) {
        textView.text = user.displayName
        Log.v("TAG", "${user.email}, ${user.displayName}")
    }

    override fun launchProfile(page: Class<ProfilePageActivity>, user: Parcelable) {
        val profileIntent = Intent(applicationContext, page)
        profileIntent.putExtra(USER, user)
        startActivity(profileIntent)
    }


}