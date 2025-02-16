package cat.itb.m78.exercices.Trivial.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.Trivial.SettingsViewModel
import cat.itb.m78.exercices.Trivial.TrivialDifficulty
import cat.itb.m78.exercices.Trivial.TrivialTheme
import cat.itb.m78.exercices.Trivial.brush
import kotlin.math.roundToInt

@Composable
fun SettingsScreen(navigateToMenuScreen: () -> Unit) {
    val settingsViewModel: SettingsViewModel = viewModel { SettingsViewModel() }
    SettingsScreenView(navigateToMenuScreen, settingsViewModel)
}

@Composable
fun SettingsScreenView(navigateToMenuScreen: () -> Unit, viewModel: SettingsViewModel){
    var expandedDifficulty by remember { mutableStateOf(false) }
    var expandedTheme by remember { mutableStateOf(false) }

    val themeOptions = TrivialTheme.entries
    val difficultyOptions = TrivialDifficulty.entries
    val roundsOptions = listOf(5, 10, 15)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(brush)
    ) {
        Column {
            TextField(
                readOnly = true,
                value = viewModel.selectedTheme.name,
                onValueChange = {},
                label = { Text("Theme") },
                trailingIcon = {
                    IconButton(onClick = { expandedTheme = !expandedTheme }) {
                        Icon(Icons.Filled.ArrowDropDown, "dropdown arrow")
                    }
                }
            )
            DropdownMenu(
                expanded = expandedTheme,
                onDismissRequest = { expandedTheme = false }
            ) {
                themeOptions.forEach { theme ->
                    DropdownMenuItem(
                        text = { Text(text = theme.name) },
                        onClick = {
                            viewModel.updateTheme(theme)
                            expandedTheme = false
                        }
                    )
                }
            }
        }
        Column {
            TextField(
                readOnly = true,
                value = viewModel.selectedDifficulty.name,
                onValueChange = {},
                label = { Text("Difficulty") },
                trailingIcon = {
                    IconButton(onClick = { expandedDifficulty = !expandedDifficulty }) {
                        Icon(Icons.Filled.ArrowDropDown, "dropdown arrow")
                    }
                }
            )
            DropdownMenu(
                expanded = expandedDifficulty,
                onDismissRequest = { expandedDifficulty = false }
            ) {
                difficultyOptions.forEach { difficulty ->
                    DropdownMenuItem(
                        text = { Text(text = difficulty.name) },
                        onClick = {
                            viewModel.updateDifficulty(difficulty)
                            expandedDifficulty = false
                        }
                    )
                }
            }
        }

        Text("Rounds", style = MaterialTheme.typography.bodyLarge)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            roundsOptions.forEach { rounds ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = viewModel.selectedRounds == rounds,
                            onClick = {
                                viewModel.updateRounds(rounds)
                            }
                        )
                        Text(text = rounds.toString())
                    }
                }
            }
        }

        Text("Time per round", style = MaterialTheme.typography.bodyLarge)

        Slider(
            value = viewModel.selectedTime,
            onValueChange = { viewModel.updateTime(it.roundToInt().toFloat()) },
            steps = 5,
            valueRange = 4f..10f
        )
        Text(text = viewModel.selectedTime.toString())

        Button(onClick = navigateToMenuScreen) {
            Text("Return to menu")
        }
    }
}