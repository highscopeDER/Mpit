package com.example.mpit.view.profileActivity

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import java.io.ByteArrayOutputStream
import kotlin.coroutines.Continuation

@InjectViewState
class ProfilePagePresenter : MvpPresenter<ProfilePageInterface>() {

    private lateinit var fireStore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        storage = FirebaseStorage.getInstance()
    }

    fun dbPullImage(image: Drawable, email: String) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        CoroutineScope(Dispatchers.IO).launch {
            val bitmap: Bitmap = (image as BitmapDrawable).bitmap
            val byteStream: ByteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream)
            val byteArray = byteStream.toByteArray()
            val imageRef = storageRef.child(email)
            val uploadTask: UploadTask = imageRef.putBytes(byteArray)
            val task = uploadTask
                .continueWithTask {
                    imageRef.downloadUrl
                }
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        viewState.message("Image uploaded")
                    }
                }
        }

    }
}
