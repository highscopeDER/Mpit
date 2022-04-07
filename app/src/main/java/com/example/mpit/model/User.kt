package com.example.mpit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val email: String,
    val name: String,
    val type: String,
): Parcelable