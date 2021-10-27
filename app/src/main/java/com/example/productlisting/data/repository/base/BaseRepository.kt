package com.example.productlisting.data.repository.base

abstract class BaseRepository {
    fun getErrorMessage(resp: BaseResponse) = resp.errorMessage
}