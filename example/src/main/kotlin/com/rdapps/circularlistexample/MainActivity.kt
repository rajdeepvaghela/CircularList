package com.rdapps.circularlistexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rdapps.circularlist.CircularList
import com.rdapps.circularlist.InfiniteCircularList
import com.rdapps.circularlistexample.ui.theme.CircularListExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircularListExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        var month by remember {
            mutableIntStateOf(1)
        }

        var year by remember {
            mutableIntStateOf(2024)
        }

        val monthList = remember {
            listOf(
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul",
                "Aug",
                "Sept",
                "Oct",
                "Nov",
                "Dec"
            )
        }

        val yearList = remember {
            (2020..2025).toList()
        }

        InfiniteCircularList(
            width = 100.dp,
            itemHeight = 40.dp,
            items = monthList,
            initialItem = monthList[month - 1],
            textColor = Color.LightGray,
            selectedTextColor = Color.Black,
            onItemSelected = { _, item ->
                val selectedMonth = monthList.indexOf(item) + 1
                month = selectedMonth
            }
        )

        CircularList(
            width = 100.dp,
            itemHeight = 40.dp,
            items = yearList,
            initialItem = year,
            textColor = Color.LightGray,
            selectedTextColor = Color.Black,
            onItemSelected = { _, item ->
                year = item
            },
            prepareDisplayItem = {
                "2K ${it % 2000}"
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    CircularListExampleTheme {
        MainScreen()
    }
}