package com.example.pz6_pr_24101_karablin_ivan

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pz6_pr_24101_karablin_ivan.ui.theme.Pz6pr24101karablinivanTheme
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val counter = mutableStateOf(0)
        val uppercase = mutableStateOf(false)

        setContent {
            HomeScreen(
                counter = counter,
                onCounterClick = { counter.value++ },
                uppercase = uppercase,
                onUppercaseChange = { uppercase.value = it }
            )
        }
    }
}
@Composable
fun HomeScreen(
    counter: State<Int>,
    onCounterClick: () -> Unit,
    uppercase: State<Boolean>,
    onUppercaseChange: (Boolean) -> Unit
) {
    val counterValue = counter.value
    val uppercaseValue = uppercase.value

    Column(modifier = Modifier.padding(16.dp)) {
        ClickCounter(
            uppercase = uppercaseValue,
            counterValue = counterValue,
            onCounterClick = onCounterClick
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = uppercaseValue,
                onCheckedChange = onUppercaseChange
            )
            Text("Uppercase")
        }
    }
}
@Composable
fun ClickCounter(
    uppercase: Boolean,
    counterValue: Int,
    onCounterClick: () -> Unit
) {
    val evenOdd = remember(uppercase) { EvenOdd(uppercase) }

    Text(
        text = "Clicks: $counterValue ${evenOdd.check(counterValue)}",
        modifier = Modifier.clickable(onClick = onCounterClick)
    )

    Log.d("ComposeLogs", "ClickCounter $counterValue, $evenOdd")
}





class EvenOdd(private val uppercase: Boolean) {
    fun check(value: Int): String {
        val result = if (value % 2 == 0) "even" else "odd"
        return if (uppercase) result.uppercase() else result
    }

    override fun toString(): String {
        return "EvenOdd(uppercase=$uppercase, hash=${hashCode()})"
    }
}

