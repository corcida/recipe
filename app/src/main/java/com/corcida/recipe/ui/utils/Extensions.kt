package com.corcida.recipe.ui.utils

import java.text.Normalizer

fun String.removeNonSpacingMarks() =
    Normalizer.normalize(this, Normalizer.Form.NFD)
        .replace("\\p{Mn}+".toRegex(), "")