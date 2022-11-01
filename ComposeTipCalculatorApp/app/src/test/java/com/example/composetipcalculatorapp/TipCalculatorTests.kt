package com.example.composetipcalculatorapp

import org.junit.Assert.assertEquals
import org.junit.Test

class TipCalculatorTests {

    /**
     * Pengujian lokal atau unit testing dilakukan untuk menguji
     * bagian kecil dari aplikasi dalam hal ini adalah kode aplikasi atau
     * fungsi pada aplikasi
     */

    @Test
    fun calculate_percent_tip_no_roundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expentedTip = "$2.00"
        val actualTip = calculateTip(amount, tipPercent, false)

        /**
         * Membandingkan hasil dari method dikode aplikasi
         * dengan hasil yang diharapkan
         */
        assertEquals(actualTip, expentedTip)
    }
}