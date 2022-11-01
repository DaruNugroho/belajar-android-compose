package com.example.composeaffirmationsapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @DrawableRes val imageResourcerId: Int,
    @StringRes val stringResourceId: Int
)
