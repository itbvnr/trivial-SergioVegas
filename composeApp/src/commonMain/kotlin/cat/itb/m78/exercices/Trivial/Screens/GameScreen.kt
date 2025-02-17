package cat.itb.m78.exercices.Trivial.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.Trivial.GameViewModel
import cat.itb.m78.exercices.Trivial.Question
import cat.itb.m78.exercices.Trivial.SettingsViewModel
import cat.itb.m78.exercices.Trivial.brush
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


@Composable
fun GameScreen(navigateToResultScreen: () -> Unit){
    val viewModel: GameViewModel = viewModel { GameViewModel() }
    val settingsData: SettingsViewModel = viewModel { SettingsViewModel() }

    GameScreenView(viewModel, settingsData, navigateToResultScreen)
}

@Composable
fun GameScreenView(viewModel: GameViewModel, settingsData: SettingsViewModel, navigateToResultScreen: () -> Unit){
    var currentProgress by remember { mutableStateOf(0f) }
    var timeLeft by remember { mutableStateOf(settingsData.selectedTime) }
    var isTimerRunning by remember { mutableStateOf(true) }
    var questionAnswered by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel.currentQuestion) {
        questionAnswered = false
        timeLeft = settingsData.selectedTime
        isTimerRunning = true
    }

    LaunchedEffect(timeLeft, isTimerRunning, questionAnswered){
        if (isTimerRunning && timeLeft > 0 && !questionAnswered){
            delay(1.seconds)
            timeLeft--
            if (timeLeft == 0f){
                isTimerRunning = false
                viewModel.checkQuestion(-1, navigateToResultScreen)
            }
        }
    }

    LaunchedEffect(Unit) {
        if (viewModel.gameFinished) {
            navigateToResultScreen()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(brush)
    ) {
        if (viewModel.mostrarResultat) Text(viewModel.roundText)

        if (viewModel.currentQuestion != null) {
            Text(viewModel.currentQuestion!!.text)
            Row {
                Button(onClick = {
                    if (!questionAnswered) {
                        isTimerRunning = false
                        questionAnswered = true
                        viewModel.checkQuestion(0, navigateToResultScreen)
                    }
                } ){Text(viewModel.currentQuestion!!.options[0])}
                Button(onClick = {
                    if (!questionAnswered) {
                        isTimerRunning = false
                        questionAnswered = true
                        viewModel.checkQuestion(1, navigateToResultScreen)
                    }
                } ){Text(viewModel.currentQuestion!!.options[1])}
            }
            Row {
                Button(onClick = {
                    if (!questionAnswered) {
                        isTimerRunning = false
                        questionAnswered = true
                        viewModel.checkQuestion(2, navigateToResultScreen)
                    }
                } ){Text(viewModel.currentQuestion!!.options[2])}
                Button(onClick = {
                    if (!questionAnswered) {
                        isTimerRunning = false
                        questionAnswered = true
                        viewModel.checkQuestion(3, navigateToResultScreen)
                    }
                } ){Text(viewModel.currentQuestion!!.options[3])}
            }

            LinearProgressIndicator(
                progress = { if (settingsData.selectedTime > 0) (settingsData.selectedTime - timeLeft).toFloat() / settingsData.selectedTime.toFloat() else 0f },
                drawStopIndicator = {},
                gapSize = 0.dp
            )

            Text("Temps restant: $timeLeft")
            Text("Ronda ${viewModel.currentRound} de ${viewModel.totalRounds}")
            Text("Punts: ${viewModel.score}")
        }

    }
}