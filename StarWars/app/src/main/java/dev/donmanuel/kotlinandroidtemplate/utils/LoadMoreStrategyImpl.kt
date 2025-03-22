package dev.donmanuel.kotlinandroidtemplate.utils

import androidx.compose.foundation.lazy.LazyListLayoutInfo

/**
 * Implementation of the [LoadMoreStrategy] interface that determines when more items should be loaded
 * based on the user's scroll position in a lazy list.
 *
 * This strategy checks if the last visible item is close to the end of the list based on a given
 * boundary value, and returns true if more items should be loaded.
 *
 * @param boundary The threshold number of items from the end of the list to trigger loading more items.
 *                 The default value is 5.
 */
class LoadMoreStrategyImpl(private val boundary: Int = 5) : LoadMoreStrategy {

    /**
     * Determines if more items should be loaded based on the current layout information.
     *
     * @param layoutInfo The [LazyListLayoutInfo] containing the visible items and total item count.
     * @return `true` if more items should be loaded, otherwise `false`.
     */
    override fun shouldLoadMore(layoutInfo: LazyListLayoutInfo): Boolean {
        val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
        val totalItemCount = layoutInfo.totalItemsCount
        return lastVisibleItem != null && lastVisibleItem.index >= totalItemCount - boundary
    }
}