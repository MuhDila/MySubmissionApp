package com.muhdila.mysubmissionapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Singer(
    val name: String,
    val description: String,
    val genre: String,
    val linkSpotify: String,
    val linkYoutube: String,
    val photo: Int
) : Parcelable