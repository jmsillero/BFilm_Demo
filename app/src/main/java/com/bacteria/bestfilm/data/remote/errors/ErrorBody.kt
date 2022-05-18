package com.bacteria.bestfilm.data.remote.errors

import com.google.gson.annotations.SerializedName

class ErrorBody {

    private val error: String? = null

    @SerializedName("error_description")
    private val errorDescription: String? = null

    fun status(): Int? {
        return error?.toInt()
    }

    fun message(): String? {
        return errorDescription
    }

}