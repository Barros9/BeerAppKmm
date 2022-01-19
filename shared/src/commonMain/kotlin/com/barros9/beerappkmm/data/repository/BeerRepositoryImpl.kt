package com.barros9.beerappkmm.data.repository

import com.barros9.beerappkmm.data.network.BeerRemoteDataSource
import com.barros9.beerappkmm.domain.model.Beer
import com.barros9.beerappkmm.domain.model.Result
import com.barros9.beerappkmm.domain.repository.BeerRepository

class BeerRepositoryImpl(
    private val beerRemoteDataSource: BeerRemoteDataSource
): BeerRepository {
    override suspend fun getBeers(search: String, page: Int): Result<List<Beer>> {
        return if (search.isEmpty()) {
            beerRemoteDataSource.getBeers(page)
        } else {
            beerRemoteDataSource.searchBeers(search, page)
        }
    }
}