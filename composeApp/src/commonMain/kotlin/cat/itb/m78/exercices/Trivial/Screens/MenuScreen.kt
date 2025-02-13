package cat.itb.m78.exercices.Trivial.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cat.itb.m78.exercices.theme.AppTheme
import m78exercices.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import m78exercices.composeapp.generated.resources.Dragonite
import m78exercices.composeapp.generated.resources.Pokemon


@Composable
internal fun MenuScreen() = AppTheme {
    Box(Modifier.fillMaxSize().background(Color.Black)) {

        Image(
            painter = painterResource(Res.drawable.Dragonite),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null
        )
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(Res.drawable.Pokemon),
                modifier = Modifier.width(200.dp).height(200.dp),
                contentDescription = null
            )
            Button(
                onClick = navigateToGameScreen,
                modifier = Modifier.height(40.dp).width(120.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E5E5E))){
                Text("New Game")
            }

            Button(
                onClick = navigateToGameScreen,
                modifier = Modifier.height(40.dp).width(120.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E5E5E)))
            { Text("Settings")
            }
        }
    }
}