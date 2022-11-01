package com.example.composewellnessapp.model

import android.content.Intent.ShortcutIconResource
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Quote(
    @DrawableRes val imageResource: Int,
    @StringRes val quoteResource: Int
)