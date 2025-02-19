package cat.itb.m78.exercices.Trivial.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.Trivial.GameViewModel
import cat.itb.m78.exercices.Trivial.brush

@Composable
fun ResultScreen(navigateToMenuScreen: () -> Unit, viewModel: GameViewModel){
    ResultScreenView(navigateToMenuScreen, viewModel)
}



@Composable
fun ResultScreenView(navigateToMenuScreen: () -> Unit, viewModel: GameViewModel){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(brush)
    ) {
        Text(text ="Puntuació total:", style = TextStyle(fontSize = 24.sp))
        Spacer(modifier = Modifier.padding(2.dp))
        Text(text =("${viewModel.scoreViewModel.currentScore}"),  style = TextStyle(fontSize = 24.sp))
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = navigateToMenuScreen,
            modifier = Modifier.height(40.dp).width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff44336))){ Text("Tornar al menú inicial") }
    }
}