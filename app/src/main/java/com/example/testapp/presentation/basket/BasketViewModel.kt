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
import java.util.Calendar


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
            totalPrice = "$totalPrice ₽"
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