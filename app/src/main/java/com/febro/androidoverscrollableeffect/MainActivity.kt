package com.febro.androidoverscrollableeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.overscroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.febro.androidoverscrollableeffect.ui.theme.AndroidOverscrollableEffectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidOverscrollableEffectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OverscrollExample()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidOverscrollableEffectTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OverscrollExample() {
    val coroScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val overscrollEffect = remember(coroScope) { VerticalOverscroll(coroScope) }

    LazyColumn(
        state = listState,
        modifier =
        Modifier.fillMaxWidth()
            .overscroll(overscrollEffect)
            .scrollable(
                orientation = Orientation.Vertical,
                reverseDirection = true,
                state = listState,
                overscrollEffect = overscrollEffect
            )
    ) {
        items(10) { index ->
            Box(modifier = Modifier.fillMaxWidth().height(100.dp).background(Color.Blue)) {
                Text("index: $index")
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}