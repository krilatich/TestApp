package com.example.testapp.presentation.basket

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Dish
import com.example.domain.usecase.DeleteDishUseCase
import com.example.domain.usecase.GetBasketDishesUseCase
import com.example.domain.usecase.UpdateAmountDishUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class BasketViewModel(
    private val getBasketDishesUseCase: GetBasketDishesUseCase,
    private val updateAmountDishUseCase: UpdateAmountDishUseCase,
    private val deleteDishUseCase: DeleteDishUseCase
) : ViewModel() {

    private var getNotesJob: Job? = null
    private val _state = mutableStateOf(BasketState())
    val state: State<BasketState> = _state

    init {
        getDishes()
    }

    private fun getDishes() {
        getNotesJob?.cancel()
        getNotesJob = getBasketDishesUseCase()
            .onEach { dishes ->
                _state.value = state.value.copy(
                    basketDishes = dishes
                )
                countPrice()
            }
            .launchIn(viewModelScope)
    }

    private fun countPrice() {
        var totalPrice = 0
        for (dish in state.value.basketDishes) {
            totalPrice += dish.price * dish.count
        }
        _state.value = _state.value.copy(
            totalPrice = totalPrice.toString() + " ₽"
        )
    }

    fun increaseDish(dish: Dish) {
        viewModelScope.launch {
            updateAmountDishUseCase(dish.copy(count = dish.count + 1))
            getDishes()
        }
    }

    fun decreaseDish(dish: Dish) {
        viewModelScope.launch {
            if (dish.count == 1)
                deleteDishUseCase(dish)
            else
                updateAmountDishUseCase(dish.copy(count = dish.count - 1))
            getDishes()
        }
    }
}