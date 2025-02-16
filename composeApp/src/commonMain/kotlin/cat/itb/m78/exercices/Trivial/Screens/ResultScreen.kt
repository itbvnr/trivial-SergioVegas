package cat.itb.m78.exercices.Trivial.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.Trivial.GameViewModel
import cat.itb.m78.exercices.Trivial.brush

@Composable
fun ResultScreen(navigateToMenuScreen: () -> Unit){
    val viewModel: GameViewModel = viewModel { GameViewModel() }

    ResultScreenView(navigateToMenuScreen, viewModel)

}

@Composable
fun ResultScreenView(navigateToMenuScreen: () -> Unit, viewModel: GameViewModel){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(brush)
    ) {
        Text("Has aconseguit")
        Text("${viewModel.score}")
        Button(onClick = navigateToMenuScreen){ Text("Tornar al menú inicial!") }
    }
}