package com.example.mpit.view.profileActivity

import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ProfilePagePresenter : MvpPresenter<ProfilePageInterface>() {

    private lateinit var fireStore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun dbPullImage(image: Uri){
        viewState.setProfileImage(image)
        val storage = FirebaseStorage.getInstance()
        val riversRef = storage.reference.child("images/${image.lastPathSegment}")
        riversRef.putFile(image)
            .addOnSuccessListener(){
                viewState.message("uploaded")
            }
    }

}
