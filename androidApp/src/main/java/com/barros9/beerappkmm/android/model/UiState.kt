package com.barros9.beerappkmm.android.model

import com.barros9.beerappkmm.domain.model.Beer

sealed class UiState {
    object Loading: UiState()
    data class Success(val data: List<Beer>) : UiState()
    data class Error(val message: String) : UiState()
}