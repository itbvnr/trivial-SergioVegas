package cat.itb.m78.exercices.Trivial

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
import kotlinx.serialization.Serializable

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
    val viewModel: GameViewModel = viewModel { GameViewModel() }
    NavHost(navController = navController, startDestination = Destination.MenuScreen){
        composable<Destination.SettingsScreen> {
            SettingsScreen (
                navigateToMenuScreen = { navController.navigate(Destination.MenuScreen) }
            )
        }
        composable<Destination.MenuScreen> {
            MenuScreen(
                navigateToGameScreen = { navController.navigate(Destination.GameScreen) },
                navigateToSettingsScreen = { navController.navigate(Destination.SettingsScreen) }
            )
        }


        composable<Destination.GameScreen> {
            GameScreen (
                viewModel = viewModel,
                navigateToResultScreen = { navController.navigate(Destination.ResultScreen) }
            )
        }

        composable<Destination.ResultScreen> {
            ResultScreen (
                viewModel = viewModel,
                navigateToMenuScreen = { navController.navigate(Destination.MenuScreen) }
            )
        }
    }
}