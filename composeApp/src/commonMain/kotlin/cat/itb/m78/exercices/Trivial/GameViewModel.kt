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

class GameViewModel(private val settingsViewModel: SettingsViewModel) : ViewModel(){
    val scoreViewModel = ScoreViewModel()

    var roundText by mutableStateOf("")
    var showResult by mutableStateOf(false)
    var currentQuestion by mutableStateOf<Question?>(null)
    var gameFinished by mutableStateOf(false)
    var currentRound by mutableStateOf(1)

    init {
        resetGame()
    }
    fun resetGame() {
        scoreViewModel.resetScore()
        currentRound = 1
        gameFinished = false
        currentQuestion = randomQuestion()
        roundText = ""
    }
    fun randomQuestion(): Question {
        var difficulty: String
        var question: Question

        do {
            question = questions.random()
            difficulty = question.category
        } while (difficulty != settingsViewModel.selectedDifficulty.toString())

        return question
    }

    fun checkQuestion(userAnswer: Int, navigateToResultScreen: () -> Unit){
        if (!gameFinished) {
            when (userAnswer) {
                -1 -> {
                    roundText = "Temps esgotat!"
                }
                currentQuestion?.correctAnswerIndex -> {
                    roundText = "Resposta correcta!"
                    scoreViewModel.incrementScore()
                }
                else -> {
                    roundText = "Resposta incorrecta..."
                }
            }
            showResult = true
            viewModelScope.launch {
                delay(2000)
                showResult = false
                if (currentRound >= settingsViewModel.selectedRounds) {
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