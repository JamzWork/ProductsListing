package com.example.productlisting.data.repository.base

import com.example.productlisting.data.Result
import com.example.productlisting.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRDS {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Result.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Result.Failure(true, null, null, Constants.Error.SOMETHING_WENT_WRONG)
                    }
                }
            }
        }
    }
}