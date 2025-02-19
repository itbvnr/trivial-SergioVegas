package cat.itb.m78.exercices.Ejemplos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val buttonDefaultColor = Color(0xFF74AB7D)

@Composable
fun CalculatorScreen(navigateToResultatScreen: () -> Unit, viewModel: CalculatorViewModel){
    CalculatorScreenView(navigateToResultatScreen, viewModel)
}

@Composable
fun CalculatorScreenView(navigateToResultatScreen: () -> Unit,viewModel: CalculatorViewModel){
    var userInput by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(brush)
    ){
        Text(
            text = viewModel.savedNumber.toString(),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xFF5D8F50))
                .width(300.dp)
                .height(220.dp)
                .padding(20.dp)
        ) {
            Row {
                Button(
                    onClick = {
                        viewModel.userOperation = 1
                        viewModel.changeWeight(0)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonDefaultColor,
                        contentColor = Color.Black
                    )
                ){
                    Text(
                        text = "+",
                        fontWeight = if (viewModel.boldButton[0]) FontWeight.Bold else FontWeight.Thin,
                        fontSize = if (viewModel.boldButton[0]) 20.sp else 15.sp
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Button(
                    onClick = {
                        viewModel.userOperation = 2
                        viewModel.changeWeight(1)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonDefaultColor,
                        contentColor = Color.Black
                    )
                ){
                    Text(
                        text = "-",
                        fontWeight = if (viewModel.boldButton[1]) FontWeight.Bold else FontWeight.Thin,
                        fontSize = if (viewModel.boldButton[1]) 20.sp else 15.sp
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Button(
                    onClick = {
                        viewModel.userOperation = 3
                        viewModel.changeWeight(2) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonDefaultColor,
                        contentColor = Color.Black
                    )
                ){
                    Text(
                        text = "*",
                        fontWeight = if (viewModel.boldButton[2]) FontWeight.Bold else FontWeight.Thin,
                        fontSize = if (viewModel.boldButton[2]) 20.sp else 15.sp
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Button(
                    onClick = {
                        viewModel.userOperation = 4
                        viewModel.changeWeight(3)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonDefaultColor,
                        contentColor = Color.Black
                    )
                ){
                    Text(
                        text = "/",
                        fontWeight = if (viewModel.boldButton[3]) FontWeight.Bold else FontWeight.Thin,
                        fontSize = if (viewModel.boldButton[3]) 20.sp else 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            TextField(
                value = userInput,
                onValueChange = { userInput = it }
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navigateToResultatScreen() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonDefaultColor,
                        contentColor = Color.Black
                    )
                ){
                    Text("End")
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Button(
                    onClick = { viewModel.operation(userInput) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonDefaultColor,
                        contentColor = Color.Black
                    )
                ){
                    Text("Operation")
                }
            }

        }
    }
}