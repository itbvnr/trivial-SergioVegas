package cat.itb.m78.exercices


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cat.itb.m78.exercices.Ejemplos.Calculator
import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    Box(Modifier.fillMaxSize()){
        Calculator();
    }
}
