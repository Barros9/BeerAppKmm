package com.barros9.beerappkmm.data.network

import com.barros9.beerappkmm.domain.model.Beer
import com.barros9.beerappkmm.domain.model.Result

interface BeerRemoteDataSource {
    suspend fun getBeers(page: Int): Result<List<Beer>>
    suspend fun searchBeers(search: String, page: Int): Result<List<Beer>>
}