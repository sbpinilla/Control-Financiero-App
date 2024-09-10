package com.sergiodev.financeControl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sergiodev.financeControl.financialRecord.ui.FinancialRecordScreen
import com.sergiodev.financeControl.financialRecord.ui.FinancialRecordViewModel
import com.sergiodev.financeControl.ui.theme.ControlFinancieroTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val financialRecordViewModel: FinancialRecordViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ControlFinancieroTheme {
                FinancialRecordScreen(financialRecordViewModel)

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ControlFinancieroTheme {
        Greeting("Android")
    }
}