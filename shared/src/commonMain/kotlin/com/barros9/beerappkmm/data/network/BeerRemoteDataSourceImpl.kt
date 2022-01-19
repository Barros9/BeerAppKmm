package com.barros9.beerappkmm.data.network

import com.barros9.beerappkmm.domain.model.Beer
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class BeerRemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : BeerDataSource(), BeerRemoteDataSource {
    override suspend fun getBeers(page: Int) = getResult {
        httpClient.get<List<Beer>>("${baseUrl}beers?page=$page")
    }

    override suspend fun searchBeers(search: String, page: Int) = getResult {
        httpClient.get<List<Beer>>("${baseUrl}beers?beer_name=$search&page=$page")
    }
}
