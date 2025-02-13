package cat.itb.m78.exercices.Trivial

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cat.itb.m78.exercices.Trivial.Screens.GameScreen
import cat.itb.m78.exercices.Trivial.Screens.
import cat.itb.m78.exercices.Trivial.Screens
import cat.itb.m78.exercices.Trivial.Screens.
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

val brush = Brush.linearGradient(listOf(Color(0xFFFAE864), Color(0xFFFAD664), Color(0xFFFAAD64) ))

@Composable
fun Trivial(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.MenuScreen){
        composable<Destination.MenuScreen> {
            MenuScreen(
                navigateToGameScreen = { navController.navigate(Destination.GameScreen) },
                navigateToSettingsScreen = { navController.navigate(Destination.SettingsScreen) }
            )
        }
        composable<Destination.SettingsScreen> {
            SettingsScreen (
                navigateToMenuScreen = { navController.navigate(Destination.MenuScreen) }
            )
        }

        composable<Destination.GameScreen> {
            GameScreen (
                navigateToResultScreen = { navController.navigate(Destination.ResultScreen) }
            )
        }

        composable<Destination.ResultScreen> {
            ResultScreen (
                navigateToMenuScreen = { navController.navigate(Destination.MenuScreen) }
            )
        }
    }
}