package com.example.mpit.view.profileActivity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import com.example.mpit.R
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class ProfilePageActivity : MvpAppCompatActivity(), ProfilePageInterface {

    @InjectPresenter
    lateinit var presenter: ProfilePagePresenter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_profile)
    }

    override fun message(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}