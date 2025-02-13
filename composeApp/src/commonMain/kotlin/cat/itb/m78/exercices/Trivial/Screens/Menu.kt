package cat.itb.m78.exercices.Trivial_App

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

            //boto 1
            Button(onClick = {

            }){
                Text("New Game")
            }

            //boton 2
            Button(onClick = {

            })
            { Text("Settings")
            }
        }
    }
}

//@Composable
//internal fun GameScreen() = AppTheme {
//    Box(Modifier.fillMaxSize().background(Color.Black)) {
//
//        Column(
//            Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        )
//        {
//           Text(
//               text =
//           )
//        }
//    }
//}

