package com.barros9.beerappkmm.android.mainfragment

import android.app.Application
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.barros9.beerappkmm.android.model.BeerChips
import com.barros9.beerappkmm.android.model.UiState
import kotlinx.coroutines.launch
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance
import com.barros9.beerappkmm.domain.usecase.GetBeersUseCase
import com.barros9.beerappkmm.domain.model.Beer
import com.barros9.beerappkmm.domain.model.Result
import com.barros9.beerappkmm.domain.model.Result.Success
import com.barros9.beerappkmm.domain.model.Result.Error

class MainViewModel(app: Application) : AndroidViewModel(app), DIAware {
    override val di by closestDI()
    private val getBeersUseCase: GetBeersUseCase by instance()

    private var listScrollPosition = 0
    val page: MutableState<Int> = mutableStateOf(1)
    val search: MutableState<String> = mutableStateOf("")
    val selectedChip: MutableState<BeerChips?> = mutableStateOf(null)
    val uiState: MutableState<UiState> = mutableStateOf(UiState.Loading)
    private var list: MutableList<Beer> = mutableListOf()

    init {
        updateUiState()
    }

    private fun updateUiState() {
        viewModelScope.launch {
            runCatching {
                uiState.value = UiState.Loading
                uiState.value = when (val result = getBeersUseCase(search.value, page.value)) {
                    is Result.Success -> {
                        list.addAll(result.data!!)
                        UiState.Success(list)
                    }
                    is Result.Error -> {
                        UiState.Error(result.message ?: "Error")
                    }
                }
            }.onFailure {
                UiState.Error("Error")
            }
        }
    }

    fun searchFromBegin() {
        selectedChip.value?.also {
            search.value = it.name
        }
        page.value = 1
        onChangeListScrollPosition(0)
        updateUiState()
    }

    fun searchNextPage() {
        if ((listScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
            page.value = page.value + 1
            updateUiState()
        }
    }

    fun onChangeListScrollPosition(position: Int) {
        listScrollPosition = position
    }

    fun onRetry() {
        updateUiState()
    }
}
