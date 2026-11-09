package com.example.pz6_pr_24101_karablin_ivan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pz6_pr_24101_karablin_ivan.ui.theme.Pz6pr24101karablinivanTheme
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val counter = mutableStateOf(0)
        setContent {
            HomeScreen(counter = counter, onCounterClick = {counter.value++})
        }
    }
}

const val TAG = "ComposeLogs"

@Composable
fun HomeScreen(
    counter: State<Int>,
    onCounterClick: () -> Unit
) {
    val counterValue = counter.value // Читаем State ТУТ
    Log.d(TAG, "HomeScreen: перезапустилась")

    Column {
        ClickCounter(counterValue = counterValue, onCounterClick = onCounterClick)

        InfoText(text = if (counterValue < 3) "More" else "Enough")
    }
}

@Composable
fun ClickCounter(
    counterValue: Int,
    onCounterClick: () -> Unit
) {
    Log.d(TAG, "ClickCounter: перезапустилась (значение = $counterValue)")
    Text(
        text = "Clicks: $counterValue",
        modifier = Modifier.clickable {
            Log.d(TAG, "--- CLICK ---")
            onCounterClick()
        }
    )
}

@Composable
fun InfoText(text: String) {
    Log.d(TAG, "InfoText: перезапустилась (текст = $text)")
    Text(text = text, fontSize = 24.sp)
}