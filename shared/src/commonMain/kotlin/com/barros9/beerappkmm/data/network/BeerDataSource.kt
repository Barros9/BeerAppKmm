package com.barros9.beerappkmm.data.network

import com.barros9.beerappkmm.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BeerDataSource {
    protected suspend fun <T> getResult(call: suspend () -> T): Result<T> =
        withContext(Dispatchers.Default) {
            return@withContext try {
                val response = call()
                Result.Success(response)
            } catch (throwable: Throwable) {
                Result.Error("Network Error $throwable")
            }
        }
}