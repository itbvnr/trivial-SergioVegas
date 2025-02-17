package cat.itb.m78.exercices.Trivial.Screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cat.itb.m78.exercices.Trivial.brush
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.pokeball
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun MenuScreen(navigateToGameScreen: () -> Unit, navigateToSettingsScreen: () -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(brush)
    ) {
        Text(
            text = "Trivial",
            fontSize = 60.sp,
            )
        Image(
            painter = painterResource(Res.drawable.pokeball),
            contentDescription = null,
            modifier = Modifier.size(150.dp).clip(CircleShape)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = navigateToGameScreen,
            modifier = Modifier.height(40.dp).width(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E5E5E)))
        { Text("New Game") }

        Spacer(modifier = Modifier.padding(6.dp))

        Button(
            onClick = navigateToSettingsScreen,
            modifier = Modifier.height(40.dp).width(120.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E5E5E)
            )) { Text("Settings") }
    }
}
