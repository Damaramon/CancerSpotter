package com.dicoding.asclepius.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CancerNews(
    val name: String,
    val judul: String,
    val description: String,
    val photo: Int
) : Parcelable
