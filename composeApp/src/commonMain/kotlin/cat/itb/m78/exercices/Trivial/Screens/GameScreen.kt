package cat.itb.m78.exercices.Trivial.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.trivial.GameViewModel
import cat.itb.m78.exercices.trivial.Question
import cat.itb.m78.exercices.trivial.brush

@Composable
fun GameScreen(navigateToResultScreen: () -> Unit){
    val viewModel: GameViewModel = viewModel { GameViewModel() }
    var currentQuestion by remember { mutableStateOf<Question?>(null) }

    LaunchedEffect(Unit){
        currentQuestion = viewModel.randomQuestion()
    }

    GameScreenView(viewModel, currentQuestion, navigateToResultScreen)
}

@Composable
fun GameScreenView(viewModel: GameViewModel, currentQuestion : Question?, navigateToResultScreen: () -> Unit){
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

        if (currentQuestion != null) {
            Text(currentQuestion.text)
            Row {
                Button(onClick = { viewModel.checkQuestion(0, navigateToResultScreen) } ){
                    Text(currentQuestion.options[0])}
                Button(onClick = { viewModel.checkQuestion(1, navigateToResultScreen) } ){
                    Text(currentQuestion.options[1])}
            }
            Row {
                Button(onClick = { viewModel.checkQuestion(2, navigateToResultScreen) } ){
                    Text(currentQuestion.options[2])}
                Button(onClick = { viewModel.checkQuestion(3, navigateToResultScreen) } ){
                    Text(currentQuestion.options[3])}
            }

            Text("Ronda ${viewModel.currentRound} de ${viewModel.totalRounds}")
            Text("Punts: ${viewModel.score}")
        }

    }
}