package com.barros9.beerappkmm.domain.repository

import com.barros9.beerappkmm.domain.model.Beer
import com.barros9.beerappkmm.domain.model.Result

interface BeerRepository {
    suspend fun getBeers(search: String, page: Int): Result<List<Beer>>
}