package com.yugesh.jetcrypto.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yugesh.jetcrypto.ui.components.pullrefresh.PullRefreshIndicator
import com.yugesh.jetcrypto.ui.components.pullrefresh.pullRefresh
import com.yugesh.jetcrypto.ui.components.pullrefresh.rememberPullRefreshState
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = getViewModel<HomeViewModel>()
    val screenUiState by homeViewModel.homeScreenUiState.collectAsState()

    var searchTextField by remember { mutableStateOf("") }
    val coinsList = screenUiState.coinsList

    val scrollState = rememberLazyListState()

    val searchedCoinList by remember(key1 = searchTextField, key2 = coinsList) {
        derivedStateOf {
            if (searchTextField.isNotEmpty()) {
                coinsList.filter {
                    it.name.contains(searchTextField, ignoreCase = true)
                }
            } else {
                coinsList
            }
        }
    }

    val pullState = rememberPullRefreshState(
        refreshing = screenUiState.isLoading,
        onRefresh = {
            homeViewModel.getCoinList()
        }
    )

    LaunchedEffect(Unit) {
        homeViewModel.getCoinList()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            content = {
                if (screenUiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(
                        state = scrollState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .pullRefresh(state = pullState),
                        content = {
                            item {
                                OutlinedTextField(
                                    value = searchTextField,
                                    onValueChange = { searchTextField = it },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            items(
                                items = searchedCoinList,
                                key = { it.id }
                            ) {
                                CardDe(it.name)
                            }
                        }
                    )
                }

                PullRefreshIndicator(
                    refreshing = screenUiState.isLoading,
                    state = pullState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
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
    Text(text = id, fontSize = 40.sp, modifier = Modifier.fillMaxWidth().height(200.dp))
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
