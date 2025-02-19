package cat.itb.m78.exercices.Ejemplos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ResultatScreen(viewModel: CalculatorViewModel){
    ResultatScreenView(viewModel)
}

@Composable
fun ResultatScreenView(viewModel: CalculatorViewModel){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(brush)
    ){
        Text(
            text = viewModel.savedNumber.toString(), //usamos variable del viewmodel
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}