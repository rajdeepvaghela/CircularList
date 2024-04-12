package com.rdapps.circularlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> CircularList(
    width: Dp,
    itemHeight: Dp,
    numberOfDisplayedItems: Int = 5,
    items: List<T>,
    initialItem: T,
    itemScaleFact: Float = 1.3f,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color,
    selectedTextColor: Color,
    onItemSelected: (index: Int, item: T) -> Unit = { _, _ -> },
    prepareDisplayItem: (T) -> String = { it.toString() }
) {
    val itemHalfHeight = LocalDensity.current.run { itemHeight.toPx() / 2f }
    val scrollState = rememberLazyListState(0)

    var lastSelectedIndex by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(initialItem) {
        val targetIndex = items.indexOf(initialItem)
        lastSelectedIndex = targetIndex
        scrollState.scrollToItem(targetIndex)
    }

    LazyColumn(
        modifier = Modifier
            .width(width)
            .height(itemHeight * numberOfDisplayedItems),
        state = scrollState,
        flingBehavior = rememberSnapFlingBehavior(
            lazyListState = scrollState
        ),
        contentPadding = PaddingValues(vertical = itemHeight * 2)
    ) {
        itemsIndexed(items) { i, item ->
            var scaleFactor by remember(i) {
                mutableFloatStateOf(1f)
            }

            Box(
                modifier = Modifier
                    .height(itemHeight)
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        val y = coordinates.positionInParent().y + itemHalfHeight

                        val parentHalfHeight = (itemHalfHeight * numberOfDisplayedItems)

                        scaleFactor = (1.2f - (abs(parentHalfHeight - y) / parentHalfHeight))
                            .coerceIn(0.5f, 1f)

                        val isSelected = scaleFactor == 1f

                        if (isSelected && lastSelectedIndex != i) {
                            onItemSelected(i, item)
                            lastSelectedIndex = i
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = prepareDisplayItem(item),
                    style = textStyle,
                    color = if (lastSelectedIndex == i) {
                        selectedTextColor
                    } else {
                        textColor
                    },
                    fontSize = textStyle.fontSize * itemScaleFact * scaleFactor,
                    modifier = Modifier
                        .alpha(scaleFactor)
                )
            }
        }
    }
}