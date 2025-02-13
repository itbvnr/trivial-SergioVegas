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
    val correctAnswerIndex: Int
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

    private val questions = listOf(
        Question(
            text = "Com evoluciona Pikachu a Raichu?",
            options = listOf("Pujar nivell", "Pedra Trueno", "No evoluciona", "Cap resposta és correcta"),
            correctAnswerIndex = 0
        ),

    )

    fun randomQuestion(): Question {
        return questions.random()
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
}