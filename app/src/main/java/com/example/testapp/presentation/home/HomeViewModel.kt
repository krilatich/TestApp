package com.example.testapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Resource
import com.example.domain.usecase.GetCategoriesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Calendar


class HomeViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val res = result.data
                    if(res!=null)
                    _state.value = HomeState(categories = res.categories)
                }
                is Resource.Error -> {
                    _state.value = HomeState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getDate(): String {
        val calendar = Calendar.getInstance()
        val rusMonth =
            when (calendar.get(Calendar.MONTH)) {
                0 -> "Января"
                1 -> "Февраля"
                2 -> "Марта"
                3 -> "Апреля"
                4 -> "Мая"
                5 -> "Июня"
                6 -> "Июля"
                7 -> "Августа"
                8 -> "Сентября"
                9 -> "Октября"
                10 -> "Ноября"
                11 -> "Декабря"
                else -> {
                    "Undefined"
                }
            }
        return "${calendar.get(Calendar.DAY_OF_MONTH)} ${rusMonth}, ${calendar.get(Calendar.YEAR)}"
    }
}