package cat.itb.m78.exercices.Ejemplos


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel() : ViewModel(){
    var savedNumber by mutableStateOf(0)
    var userOperation by mutableStateOf(0)
    val boldButton = mutableStateListOf(false, false, false, false)

    private fun addNumber(userInput: Int){
        savedNumber += userInput
    }

    private fun subtractNumber(userInput: Int){
        savedNumber -= userInput
    }

    private fun multiplyNumber(userInput: Int){
        savedNumber *= userInput
    }

    private fun divideNumber(userInput: Int){
        savedNumber /= userInput
    }

    private fun checkNumber(userAnswer: String) : Boolean{
        return userAnswer.toIntOrNull() != null
    }

    private fun selectOperation(userInput: Int){
        when (userOperation){
            1 -> addNumber(userInput)
            2 -> subtractNumber(userInput)
            3 -> multiplyNumber(userInput)
            4 -> divideNumber(userInput)
        }
    }

    fun operation(userInput: String){
        if (checkNumber(userInput)) {
            val parsedInput = userInput.toInt()
            selectOperation(parsedInput)
        }
    }

    fun changeWeight(selectedButton: Int){
        for (item in 0..3){
            boldButton[item] = false
        }
        boldButton[selectedButton] = true
    }



}