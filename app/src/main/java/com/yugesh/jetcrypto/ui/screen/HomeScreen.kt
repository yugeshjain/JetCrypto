package com.yugesh.jetcrypto.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = getViewModel<HomeViewModel>()
    val screenUiState by homeViewModel.homeScreenUiState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getCoinList()
    }

    Scaffold {
        LazyColumn(
            content = {
                items(screenUiState.coinsList) {
                    CardDe(it.id)
                }
            }
        )
    }
}

@Composable
fun CardDe(
    id: String
) {
    var currentId by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    LaunchedEffect(currentId == null) {
        if (currentId == null) {
            currentId = id
            println(currentId)
        }
    }
    Text(text = id, fontSize = 40.sp, modifier = Modifier.size(200.dp))
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
