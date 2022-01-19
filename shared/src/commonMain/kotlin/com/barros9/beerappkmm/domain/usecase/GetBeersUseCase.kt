package com.barros9.beerappkmm.domain.usecase

import com.barros9.beerappkmm.domain.model.Beer
import com.barros9.beerappkmm.domain.model.Result
import com.barros9.beerappkmm.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow

class GetBeersUseCase(
    private val repository: BeerRepository
) {
    suspend operator fun invoke(search: String, page: Int): Result<List<Beer>> {
        return repository.getBeers(search, page)
    }
}