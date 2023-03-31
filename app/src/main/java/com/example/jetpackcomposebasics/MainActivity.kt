package com.example.jetpackcomposebasics

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBasicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = listOf("World", "Compose")) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            for (name in names) {
                Greeting(name = name)
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding = if (expanded) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(
                    text = "Hello,"
                )
                Text(
                    text = name
                )
            }
            Button(
                onClick = { expanded = !expanded },
                colors = ButtonDefaults.outlinedButtonColors(Color.White)
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(modifier = Modifier.padding(vertical = 24.dp),
            onClick = { onContinueClicked() }) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    JetpackComposeBasicsTheme {
        Surface {
            OnboardingScreen(onContinueClicked = { })
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    JetpackComposeBasicsTheme {
        MyApp()
    }
}