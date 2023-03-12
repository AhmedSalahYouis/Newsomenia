package com.asalah.newsomenia.core.domain.common.usecase

import com.squareup.moshi.Moshi
import com.asalah.newsomenia.core.domain.common.entity.GeneralError
import com.asalah.newsomenia.core.util.log.showLog
import com.asalah.newsomenia.core.util.network.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class ApiUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    /** Executes the use case asynchronously and returns a [ApiResult].
     *
     * @return a [ApiResult].
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P): ApiResult<R> =
        try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                            showLog("api raw", it.raw().toString())
                            if (it.isSuccessful) {
                                if (it.code() == 204 || it.code() == 205) {
                                    ApiResult.SuccessNoContent
                                } else {
                                    ApiResult.Success(it.body()!!)
                                }
                            } else {
                                try {
                                    val errorBody =
                                        it.errorBody()?.string() ?: "Unknown HTTP error body"
                                    val moshi = Moshi.Builder().build()
                                    val adapter = moshi.adapter(Object::class.java)
                                    val errorMessage = adapter.fromJson(errorBody)
                                    showLog("api server error", errorMessage.toString())
                                    ApiResult.ServerError(errorMessage as GeneralError)
                                } catch (exception: Exception) {
                                    ApiResult.Error(Exception("Unknown Error"))
                                }
                            }
                        }
                    }
        } catch (e: Exception) {
            showLog("api unknown error", e.toString())
            ApiResult.Error(e)
        }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): Response<R>
}
