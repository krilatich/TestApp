package com.example.testapp.presentation.category

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Dish
import com.example.domain.models.Resource
import com.example.domain.usecase.AddDishToBasketUseCase
import com.example.domain.usecase.GetDishesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update


class CategoryViewModel(
    private val getDishesUseCase: GetDishesUseCase,
    private val addDishToBasketUseCase: AddDishToBasketUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state
    private val _dishDialogState = MutableStateFlow(DialogState())
    val dishDialogState: StateFlow<DialogState> = _dishDialogState.asStateFlow()

    init {
        getDishes(state.value.chosenTag, updateTags = true)
    }

    fun getDishes(chosenTag: String, updateTags: Boolean = false) {
        getDishesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val res = result.data
                    if (res != null) {
                        val dishes: List<Dish> = res.dishes.filter { it.tegs.contains(chosenTag) }
                        _state.value = _state.value.copy(
                            dishes = dishes,
                            isLoading = false,
                            chosenTag = chosenTag
                        )
                        if (updateTags)
                            _state.value = _state.value.copy(
                                tags = getTags()
                            )
                    }
                }

                is Resource.Error -> {
                    _state.value = CategoryState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                        chosenTag = chosenTag,
                        dishes = emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onDishClicked(dish: Dish) {
        _dishDialogState.update {
            it.copy(
                isOpen = true,
                dish = dish
            )
        }
    }

    fun onAddDishClicked(dish: Dish) {
        addDishToBasketUseCase(dish).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _dishDialogState.update {
                        it.copy(
                            isAdded = true
                        )
                    }
                }

                is Resource.Error -> {
                    _state.value = CategoryState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _dishDialogState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
        // Continue with executing the confirmed action
    }

    fun onDishDialogDismiss() {
        _dishDialogState.update {
            it.copy(
                isOpen = false
            )
        }
    }

    private fun getTags(): List<String> {
        val tags = mutableSetOf<String>()
        for (dish in state.value.dishes)
            for (tag in dish.tegs)
                tags.add(tag)
        return tags.toList()
    }
}