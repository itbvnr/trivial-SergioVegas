package cat.itb.m78.exercices.Trivial

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class TrivialDifficulty{ Easy, Normal, Hard }

data class TrivialSettings(
    val difficulty: TrivialDifficulty = TrivialDifficulty.Easy,
    val questionsPerGame: Int = 10,
    val timePerGame: Float = 10f
)

data object TrivialSettingsManager{
    private var settings = TrivialSettings()
    fun update(newSettings: TrivialSettings){
        settings = newSettings
    }
    fun get() = settings
}

class SettingsViewModel: ViewModel(){
    var selectedDifficulty by mutableStateOf(TrivialSettingsManager.get().difficulty)
    var selectedRounds by mutableStateOf(TrivialSettingsManager.get().questionsPerGame)
    var selectedTime by mutableStateOf(TrivialSettingsManager.get().timePerGame)

    fun updateDifficulty(difficulty: TrivialDifficulty) {
        selectedDifficulty = difficulty
        saveSettings()
    }

    fun updateRounds(rounds: Int) {
        selectedRounds = rounds
        saveSettings()
    }

    fun updateTime(time: Float){
        selectedTime = time
        saveSettings()
    }
    private fun saveSettings() {
        TrivialSettingsManager.update(
            TrivialSettings(
                difficulty = selectedDifficulty,
                questionsPerGame = selectedRounds,
                timePerGame = selectedTime
            )
        )
    }

}