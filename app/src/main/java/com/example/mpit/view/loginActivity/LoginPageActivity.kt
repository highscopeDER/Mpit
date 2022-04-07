package com.example.mpit.view.loginActivity

import android.os.Bundle
import com.example.mpit.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class LoginPageActivity : MvpAppCompatActivity(), LoginPageInterface {

    @InjectPresenter
    lateinit var presenter: LoginPagePresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)




    }



}