package dev.donmanuel.kotlinandroidtemplate.utils

import androidx.compose.foundation.lazy.LazyListLayoutInfo

interface LoadMoreStrategy {
    fun shouldLoadMore(layoutInfo: LazyListLayoutInfo): Boolean
}