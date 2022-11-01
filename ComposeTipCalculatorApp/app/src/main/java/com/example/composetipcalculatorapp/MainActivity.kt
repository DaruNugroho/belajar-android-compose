package com.example.composetipcalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetipcalculatorapp.ui.theme.ComposeTipCalculatorAppTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTipCalculatorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

@Composable
fun TipTimeScreen() {
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var tipInput by remember { mutableStateOf("") }
    val tipPercent: Double = tipInput.toDoubleOrNull() ?: 0.0
    val focusManager = LocalFocusManager.current // mengontrol focus di compose (keyboard)
    var roundUp by remember { mutableStateOf(false) }
    val tip = calculateTip(amount, tipPercent, roundUp)

    Column(
        Modifier
            .wrapContentSize(Alignment.TopCenter)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.calculate_tip))

        Spacer(modifier = Modifier.height(16.dp))

        EditNumberField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = R.string.bill_amount,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, // untuk keyboard yang angka
                imeAction = ImeAction.Next // menampilkan tombol next pada keyboard
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down) // untuk memindahkan fokus ke composeable berikutnya
            })
        )

        Spacer(modifier = Modifier.height(8.dp))

        EditNumberField(
            value = tipInput, // menampung nilai dari tipInput dan menampilkan ke TextField
            onValueChange = { tipInput = it  }, // menerima input dari user dan disimpan ke tipInput
            label = R.string.how_was_the_service,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, // untuk keyboard yang angka
                imeAction = ImeAction.Done // menampilkan tombol done/ceklis dikeyboard
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus() // menghapus focus dan mengilangkan keyboard
            })
        )

        RoundTheTipRow(
            roundUp = roundUp,
            onRoundUpChanged = { roundUp = it},
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = R.string.tip_amount, tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
private fun EditNumberField(
    @StringRes label: Int, // agar yang dapat masuk adalah resource dari string
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = label)) }, // menambahkan label
        modifier = Modifier.fillMaxWidth(),
        singleLine = true, // agar tidak bisa melebar kebawah saat di tekan enter
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}

@Composable
fun RoundTheTipRow(
    roundUp : Boolean,
    onRoundUpChanged : (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp), // mengatur tinggi tulisan Round up tip
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.round_up_tip))
        Switch(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End), // untuk menggeser swith ke kanan layar
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.DarkGray, // mengatur warna bulatan switch
            )
        )
    }
}

@VisibleForTesting // untuk keperluan testing
internal fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean,
): String {
    var tip = tipPercent / 100 * amount
    if (roundUp) tip = kotlin.math.ceil(tip) // untuk membulatkan bilangan pecahan / double
    return NumberFormat.getCurrencyInstance().format(tip) // memformat bilangan ke mata uang
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTipCalculatorAppTheme {
        TipTimeScreen()
    }
}