package com.example.composetipcalculatorapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.composetipcalculatorapp.ui.theme.ComposeTipCalculatorAppTheme
import org.junit.Rule
import org.junit.Test

class TipUiTests {

    @get:Rule
    val composeTestRule = createComposeRule() // instance dari aplikasi UI / konteks nya

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            ComposeTipCalculatorAppTheme {
                TipTimeScreen()
            }
        }

        val amount = 10.00
        val tipPercent = 20.00
        val expentedTip = "$2.00"
     //   calculateTip(amount, tipPercent, true)

        // untuk mengakses komponen ui di aplikasi (TextField dengan tulisan seperti dibawah)
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput(amount.toString()) // untuk mengisi nilai di TextField Cost of Service
        composeTestRule.onNodeWithText("Tip (%)")
            .performTextInput(tipPercent.toString())
        composeTestRule.onNodeWithText("Tip Amount: $expentedTip") // menentukan hasil yang diharapkan
            .assertExists() // untuk pernyataan bahwa node dengan teks (hasil yang diharapkan diatas) tersebut ada
    }
}