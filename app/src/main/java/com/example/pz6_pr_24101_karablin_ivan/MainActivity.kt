package com.example.pz6_pr_24101_karablin_ivan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pz6_pr_24101_karablin_ivan.ui.theme.Pz6pr24101karablinivanTheme
import androidx.compose.runtime.State

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val counter = mutableStateOf(0);
        setContent {
            HomeScreen(
                counter = counter,
                onCounterClick = {counter.value++}
            )
        }
    }
}

@Composable
fun HomeScreen(counter: State<Int>, onCounterClick: ()-> Unit){
    val counterValue = counter.value
    Text(
        text = "Clicks: $counterValue",
        modifier = Modifier.clickable(onClick = onCounterClick)
    )

}

