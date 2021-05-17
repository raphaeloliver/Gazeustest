package com.example.gazeuslibrary.models

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class UserRepos(
    @SerializedName("id") val id : Int,
    @SerializedName("node_id") val nodeId : String,
    @SerializedName("name") val name : String,
    @SerializedName("full_name") val fullName : String,
    @SerializedName("private") val private : Boolean,
    @SerializedName("owner") val owner : UserReposOwner
) : Parcelable

@Keep
@Parcelize
data class UserReposOwner(
    @SerializedName("login") val login : String
): Parcelable