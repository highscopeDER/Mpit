package com.example.mpit.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class FirebaseUsers {

    private lateinit var db: FirebaseAuth
    private var currentUser: FirebaseUser? = null

    fun checkCurrentUser(): Boolean{
        db = FirebaseAuth.getInstance()
        if (db.currentUser != null) { currentUser = db.currentUser }
        return db.currentUser != null
    }

    fun getCurrentUser(): FirebaseUser? {
       return currentUser
    }

}