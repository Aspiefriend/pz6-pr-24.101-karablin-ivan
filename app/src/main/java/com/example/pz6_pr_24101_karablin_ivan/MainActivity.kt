package com.example.pz6_pr_24101_karablin_ivan

import android.content.res.Configuration
import android.hardware.camera2.params.ColorSpaceTransform
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pz6_pr_24101_karablin_ivan.ui.theme.Pz6pr24101karablinivanTheme
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val counter = mutableStateOf(0)
        val checked = mutableStateOf(false)
        val testCompose = mutableStateOf(0)
        val forceUpdate = mutableStateOf(0)
        setContent {
            Pz6pr24101karablinivanTheme() {
                Surface (modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AllFromLections(
                        counter = counter,
                        onCounterChanges = { counter.value++ },
                        checked = checked,
                        onCheckedChanges = { newCheckState -> checked.value = newCheckState },
                        testCompose = testCompose,
                        onTestCompose = {
                            if (testCompose.value < 5) {
                                testCompose.value++
                            } else {
                                testCompose.value = 5
                                forceUpdate.value++
                            }
                        },
                        forceUpdate = forceUpdate
                    )
                }

            }
        }
    }
}

@Composable
fun AllFromLections(

    counter : State<Int>,
    onCounterChanges: ()-> Unit,
    checked : State<Boolean>,
    onCheckedChanges: (Boolean) -> Unit,
    testCompose: State<Int>,
    onTestCompose: ()-> Unit,
    forceUpdate: State<Int>,


    ){
    var textField by remember{mutableStateOf("")}
    val counterValue = counter.value
    var counterDelegate by remember{ mutableStateOf(0)}
    var checkboxViewCounterDeleagate by remember{mutableStateOf(false)}
    val checkedValue = checked.value
    val testComposeValue = testCompose.value
    val forceUpdateValue = forceUpdate.value
    var logicalVariable by remember {mutableStateOf(false)}
    var text by remember {mutableStateOf("")}
    val classTest = remember(logicalVariable) { Message(logicalVariable) }


    Column (modifier = Modifier
        .padding(16.dp)){
        Text(
            text = "Практическая работа 6",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth().padding(vertical =4.dp),
            tonalElevation = 2.dp
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkboxViewCounterDeleagate,
                    onCheckedChange = { checkboxViewCounterDeleagate = it })
                Text(text = "Счётчик из Activity")
                if (checkboxViewCounterDeleagate) {

                    Text(
                        text = "[Клик +1 $counterValue]",
                        modifier = Modifier.clickable(onClick = onCounterChanges),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            tonalElevation = 2.dp
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Checkbox(checked = checkedValue, onCheckedChange = onCheckedChanges)
                Text(text = "Счётчик из 10 задания")
                if (checkedValue) {
                    Text(
                        text = "[Клик +1 $counterDelegate]",
                        modifier = Modifier.clickable(
                            onClick = { counterDelegate++ },
                        ), color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = textField, onValueChange = {textField = it},)
        Spacer(modifier = Modifier.height(8.dp))
        Log.d("TEST", "AllFromLections (forceUpdate = $forceUpdateValue)")
        TestCompose(composeTest =  testComposeValue, onComposeTest =  onTestCompose)
        Spacer(modifier = Modifier.height(8.dp))
        Surface (
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            tonalElevation = 2.dp
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = logicalVariable, onCheckedChange = { logicalVariable = it })
                Text(text = "isUppercase")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = text, onValueChange = { text = it },)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = classTest.returnMessage(text),)

    }
}

@Composable
fun TestCompose (composeTest: Int,
                 onComposeTest: ()-> Unit) {



    Log.d("TEST", "TestCompose")

    Text(text="Счётчик $composeTest",modifier = Modifier.clickable(onClick = onComposeTest).padding(start = 12.dp))
}

@Preview(
    name = "Light Mode"
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES 
)
@Composable
fun PreviewAllFromLections(){
    val counter = remember {mutableStateOf(0)}
    val checked =remember{ mutableStateOf(false)}
    val testCompose = remember{mutableStateOf(0)}
    val forceUpdate = remember {  mutableStateOf(0)}
    Pz6pr24101karablinivanTheme() {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AllFromLections(
                counter = counter,
                onCounterChanges = { counter.value++ },
                checked = checked,
                onCheckedChanges = { newCheckState -> checked.value = newCheckState },
                testCompose = testCompose,
                onTestCompose = {
                    if (testCompose.value < 5) {
                        testCompose.value++
                    } else {
                        testCompose.value = 5
                        forceUpdate.value++
                    }
                },
                forceUpdate = forceUpdate
            )
        }
    }



}
class Message(private val isUppercase: Boolean){
    fun returnMessage(text:String) :String {
        if (isUppercase){
            return "[${text.uppercase()}]; hashCode = ${hashCode().toHexString()}"

        }
        else {
            return "[${text.reversed()}]; hashCode = ${hashCode().toHexString()}"
        }
    }

}

