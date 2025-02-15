package cat.itb.m78.exercices.Trivial

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val category: String
)

class GameViewModel() : ViewModel(){
    private val settingsData = TrivialSettingsManager.get()

    val totalRounds: Int = settingsData.questionsPerGame

    var roundText by mutableStateOf("")
    var score by mutableStateOf(0)
    var mostrarResultat by mutableStateOf(false)
    var currentQuestion by mutableStateOf<Question?>(null)
    var gameFinished by mutableStateOf(false)
    var currentRound by mutableStateOf(1)

    fun randomQuestion(): Question {
        var theme: String
        var question: Question

        do {
            question = questions.random()
            theme = question.category
        } while (theme != settingsData.theme.toString())

        return question
    }

        fun checkQuestion(userAnswer: Int, navigateToResultScreen: () -> Unit){
        if (!gameFinished) {
            if (userAnswer == currentQuestion?.correctAnswerIndex) {
                roundText = "Resposta correcta!"
                score++
            } else {
                roundText = "Resposta incorrecta..."
            }
            mostrarResultat = true
            viewModelScope.launch {
                delay(5000)
                mostrarResultat = false
                if (currentRound >= totalRounds) {
                    gameFinished = true
                    navigateToResultScreen()
                } else {
                    currentQuestion = randomQuestion()
                    currentRound++
                }
            }
        }
    }

    suspend fun loadProgress(updateProgress: (Float) -> Unit) {
        for (i in 1..50) {
            updateProgress(i.toFloat() / 100)
            delay(100)
        }
    }
}