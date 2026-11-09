package com.example.pz6_pr_24101_karablin_ivan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.State
import androidx.compose.ui.unit.dp
import com.example.pz6_pr_24101_karablin_ivan.ui.theme.Pz6pr24101karablinivanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val checked =  mutableStateOf(true)
        val text = mutableStateOf("some text")
        setContent {
            HomeScreen(checked = checked,  onCheckedChange = { newCheckedValue ->
                checked.value = newCheckedValue
            },text = text, onValueChange = { newText-> text.value = newText}
            )

        }
    }
}

@Composable
fun HomeScreen(
    checked: State<Boolean>,
    onCheckedChange: (Boolean) -> Unit,
    text: State<String>,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        val checkedValue = checked.value
        Row(verticalAlignment = CenterVertically) {
            Checkbox(checked = checkedValue, onCheckedChange = onCheckedChange)
            Text(
                "Some checkbox text",
                fontSize = 18.sp,
                modifier = Modifier.clickable { onCheckedChange(!checked.value) })
        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = text.value,
            onValueChange = onValueChange,)
    }
}

