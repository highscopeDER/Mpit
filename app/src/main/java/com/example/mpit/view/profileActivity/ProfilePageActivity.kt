package com.example.mpit.view.profileActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mpit.R
import com.example.mpit.model.User
import com.example.mpit.view.mainActivity.USER
import com.google.firebase.auth.FirebaseUser
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class ProfilePageActivity : MvpAppCompatActivity(), ProfilePageInterface {

    @InjectPresenter
    lateinit var presenter: ProfilePagePresenter
    lateinit var profileImageView: ImageView
    lateinit var profileNameView: TextView
    lateinit var profileType: TextView
    lateinit var userEmail: String

    private val GALERY_REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileNameView = findViewById(R.id.nameTextView)
        profileType = findViewById(R.id.profileType)

        val user = intent.getParcelableExtra<User>(USER)
        if (user != null){
            val userName = user.name
            profileNameView.text = userName
            userEmail = user.email
            profileType.text = user.type
        }
        profileImageView = findViewById(R.id.profileImage)
        profileImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALERY_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALERY_REQUEST){
            if (resultCode == RESULT_OK){
                if (data != null){
                    val selectedImage = data.data
                    if (selectedImage != null) {
                        profileImageView.setImageURI(selectedImage)

                        presenter.dbPullImage(profileImageView.drawable, userEmail)
                    }
                }
            }
        }
    }

    override fun setProfileImage(file: Uri) {
        profileImageView.setImageURI(file)
    }

    override fun message(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}