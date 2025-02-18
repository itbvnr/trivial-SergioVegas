package cat.itb.m78.exercices.Trivial.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cat.itb.m78.exercices.Trivial.GameViewModel
import cat.itb.m78.exercices.Trivial.SettingsViewModel
import cat.itb.m78.exercices.Trivial.brush
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun GameScreen(navigateToResultScreen: () -> Unit, viewModel: GameViewModel, settingsData: SettingsViewModel){
    GameScreenView(viewModel, settingsData, navigateToResultScreen)
}

@Composable
fun GameScreenView(viewModel: GameViewModel, settingsData: SettingsViewModel, navigateToResultScreen: () -> Unit){
    var timeLeft by remember { mutableStateOf(settingsData.selectedTime) }
    var timerRunning by remember { mutableStateOf(true) }
    var questionAnswered by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.currentQuestion) {
        questionAnswered = false
        timeLeft = settingsData.selectedTime
        timerRunning = true
    }

    LaunchedEffect(timeLeft, timerRunning, questionAnswered){
        if (timerRunning && timeLeft > 0 && !questionAnswered){
            delay(1.seconds)
            timeLeft--
            if (timeLeft == 0f){
                timerRunning = false
                viewModel.checkQuestion(-1) { navigateToResultScreen() }
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
        if (viewModel.showResult) Text( text =viewModel.roundText,  style = TextStyle(fontSize = 24.sp))
        Spacer(modifier = Modifier.padding(2.dp))

        if (viewModel.currentQuestion != null) {
            Text( text =viewModel.currentQuestion!!.text,  style = TextStyle(fontSize = 24.sp))
            Spacer(modifier = Modifier.padding(3.dp))
            Row {
                Button(onClick = {
                    if (!questionAnswered) {
                        timerRunning = false
                        questionAnswered = true
                        viewModel.checkQuestion(0) { navigateToResultScreen() }}
                }, modifier = Modifier.height(40.dp).width(250.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff44336)))
                { Text(viewModel.currentQuestion!!.options[0]) }
            }
            Spacer(modifier = Modifier.padding(3.dp))
                Row {
                Button(onClick = {
                    if (!questionAnswered) {
                        timerRunning = false
                        questionAnswered = true
                        viewModel.checkQuestion(1) { navigateToResultScreen() }}
                },
                    modifier = Modifier.height(40.dp).width(250.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff44336)))
                {Text(viewModel.currentQuestion!!.options[1])}
            }
            Spacer(modifier = Modifier.padding(3.dp))
            Row {
                Button(onClick = {
                    if (!questionAnswered) {
                        timerRunning = false
                        questionAnswered = true
                        viewModel.checkQuestion(2) { navigateToResultScreen() }}
                },
                    modifier = Modifier.height(40.dp).width(250.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff44336)))
                { Text(viewModel.currentQuestion!!.options[2]) }
            }
            Spacer(modifier = Modifier.padding(3.dp))
            Row {
                Button(onClick = {
                    if (!questionAnswered) {
                        timerRunning = false
                        questionAnswered = true
                        viewModel.checkQuestion(3) { navigateToResultScreen() }}
                },
                    modifier = Modifier.height(40.dp).width(250.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff44336)))
                {Text(viewModel.currentQuestion!!.options[3])}
            }
            Spacer(modifier = Modifier.padding(6.dp))

            LinearProgressIndicator(
                progress = { if (settingsData.selectedTime > 0) (settingsData.selectedTime - timeLeft)/ settingsData.selectedTime else 0f },
                drawStopIndicator = {},
                gapSize = 0.dp,
                modifier = Modifier.height(20.dp),
                color = Color.Red,
                trackColor = Color.LightGray
            )

            Text("Temps restant: $timeLeft")
            Text("Ronda ${viewModel.currentRound} de ${settingsData.selectedRounds}")
            Text("Punts: ${viewModel.scoreViewModel.currentScore}")
        }

    }
}