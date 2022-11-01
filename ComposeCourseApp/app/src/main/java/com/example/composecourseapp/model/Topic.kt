package com.example.composecourseapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val stringResourceId: Int,
    val Course: Int,
    @DrawableRes val imageResourceId: Int,
)
