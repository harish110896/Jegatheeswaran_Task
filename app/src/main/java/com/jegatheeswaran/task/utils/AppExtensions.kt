package com.jegatheeswaran.task.utils

import android.annotation.SuppressLint
import androidx.navigation.NavController
import kotlin.math.absoluteValue

fun NavController.singleTopNavigator(route: String) {
    this.navigate(route) {
        graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                inclusive = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@SuppressLint("DefaultLocale")
fun Double.toRupeeString(): String {
    return String.format("\u20B9 %.2f", this)
}

@SuppressLint("DefaultLocale")
fun Double.toSignedRupeeString(positive: Boolean): String {
    val formatted = String.format("%.2f", this.absoluteValue)
    return if (positive) "\u20B9 $formatted" else "-\u20B9 $formatted"
}

fun String.withAsterisk(): String {
    return "$this*"
}
