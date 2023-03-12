package com.asalah.newsomenia.core.util.network

import com.asalah.newsomenia.core.domain.common.entity.GeneralError


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ApiResult<out R> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()
    data class ServerError(val errorBody: GeneralError) : ApiResult<Nothing>()
    object Init : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
    object SuccessNoContent : ApiResult<Nothing>()
}

val <T> ApiResult<T>.data: T?
    get() = (this as? ApiResult.Success)?.data
