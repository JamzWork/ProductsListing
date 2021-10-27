package com.example.productlisting.data

import com.example.productlisting.utils.Constants
import okhttp3.ResponseBody

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val errorMessage: String = Constants.Error.SOMETHING_WENT_WRONG
        ) : Result<Nothing>()
}