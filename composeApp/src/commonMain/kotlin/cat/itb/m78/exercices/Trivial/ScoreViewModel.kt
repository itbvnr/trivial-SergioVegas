package cat.itb.m78.exercices.Trivial


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class TrivialScore(
    val score: Int = 0
)

data object TrivialScoreManager {
    private var gameScore = TrivialScore()
    fun update(newScore: TrivialScore) {
        gameScore = newScore
    }
    fun get() = gameScore
}

class ScoreViewModel: ViewModel() {
    var currentScore by mutableStateOf(TrivialScoreManager.get().score)
        private set

    fun incrementScore() {
        currentScore += 1
        saveScore()
    }

    fun resetScore() {
        currentScore = 0
        saveScore()
    }

    private fun saveScore() {
        TrivialScoreManager.update(
            TrivialScore(score = currentScore)
        )
    }
}