package com.example.cryptoapp.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class AffirmationModel(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)