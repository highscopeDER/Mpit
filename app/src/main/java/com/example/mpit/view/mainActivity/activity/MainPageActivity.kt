package com.example.mpit.view.mainActivity.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import com.example.mpit.R
import com.example.mpit.view.mainActivity.fragmentVolunteer.VolunteerMainPage
import com.example.mpit.view.profileActivity.ProfilePageActivity
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseUser
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

const val USER = "user"

class MainPageActivity : MvpAppCompatActivity(), MainPageInterface {

    @InjectPresenter
    lateinit var presenter: MainPagePresenter
    lateinit var fragmentContainerView: FragmentContainerView
    lateinit var fragmentManager: FragmentManager
    lateinit var volunteerPage: VolunteerMainPage
    lateinit var profileButton: Button
    lateinit var signOutButton: Button


    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        if (res.resultCode == RESULT_OK) {
            presenter.openProfilePage()
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
        fragmentContainerView = findViewById(R.id.fragmentContainer)
        fragmentManager = supportFragmentManager
        volunteerPage = VolunteerMainPage()
        fragmentManager.beginTransaction().apply {
            replace(fragmentContainerView.id, volunteerPage)
            commit()
        }
        profileButton = findViewById(R.id.profileButton)
        profileButton.setOnClickListener {
            presenter.openProfilePage()
        }
        signOutButton = findViewById(R.id.signOutButton)
        signOutButton.setOnClickListener {
            presenter.userSignOut()
        }
    }

    override fun launchUserLogin(intent: Intent) {
        signInLauncher.launch(intent)
    }

    override fun updateUser(user: FirebaseUser) {
        Log.v("TAG", "${user.email}, ${user.displayName}")
    }

    override fun launchProfile(page: Class<ProfilePageActivity>, user: Parcelable) {
        val profileIntent = Intent(applicationContext, page)
        profileIntent.putExtra(USER, user)
        startActivity(profileIntent)
    }


}