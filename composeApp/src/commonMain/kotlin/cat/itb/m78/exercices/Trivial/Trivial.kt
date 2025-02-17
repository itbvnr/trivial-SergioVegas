package cat.itb.m78.exercices.Trivial

import kotlinx.serialization.Serializable
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.Trivial.Screens.GameScreen
import cat.itb.m78.exercices.Trivial.Screens.MenuScreen
import cat.itb.m78.exercices.Trivial.Screens.SettingsScreen
import cat.itb.m78.exercices.Trivial.Screens.ResultScreen
import cat.itb.m78.exercices.Trivial.SettingsViewModel


object Destination{
    @Serializable
    data object MenuScreen
    @Serializable
    data object SettingsScreen
    @Serializable
    data object GameScreen
    @Serializable
    data object ResultScreen
}

val brush = Brush.verticalGradient(listOf(Color(0xfff44336), Color(0xFF5E5E5E), Color(0xffffffff) ))

@Composable
fun Trivial(){
    val navController = rememberNavController()
    val settingsViewModel: SettingsViewModel = viewModel { SettingsViewModel() }
    val gameViewModel: GameViewModel = viewModel { GameViewModel(settingsViewModel) }

    NavHost(navController = navController, startDestination = Destination.MenuScreen){

        composable<Destination.SettingsScreen> {
            SettingsScreen (
                navigateToMenuScreen = { navController.navigate(Destination.MenuScreen) },
                settingsViewModel = settingsViewModel
            )
        }
        composable<Destination.MenuScreen> {
            MenuScreen(
                navigateToGameScreen = { navController.navigate(Destination.GameScreen) },
                navigateToSettingsScreen = { navController.navigate(Destination.SettingsScreen) },
                gameViewModel = gameViewModel
            )
        }

        composable<Destination.GameScreen> {
            GameScreen (
                navigateToResultScreen = { navController.navigate(Destination.ResultScreen) },
                viewModel = gameViewModel,
                settingsData = settingsViewModel
            )
        }

        composable<Destination.ResultScreen> {
            ResultScreen (
                viewModel = gameViewModel,
                navigateToMenuScreen = { navController.navigate(Destination.MenuScreen) }
            )
        }
    }
}