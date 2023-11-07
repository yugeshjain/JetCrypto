package com.yugesh.jetcrypto.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yugesh.jetcrypto.R
import com.yugesh.jetcrypto.ui.components.FlippableCard
import com.yugesh.jetcrypto.ui.components.pullrefresh.PullRefreshIndicator
import com.yugesh.jetcrypto.ui.components.pullrefresh.pullRefresh
import com.yugesh.jetcrypto.ui.components.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = getViewModel<HomeViewModel>()
    val screenUiState by homeViewModel.homeScreenUiState.collectAsState()

    val searchTextField = remember { mutableStateOf("") }
    val coinsList = screenUiState.coinsList

    val gridScrollState = rememberLazyGridState()


    val searchedCoinList by remember(key1 = searchTextField, key2 = coinsList) {
        derivedStateOf {
            if (searchTextField.value.isNotEmpty()) {
                coinsList.filter {
                    it.name?.contains(searchTextField.value, ignoreCase = true) ?: false
                }
            } else {
                coinsList
            }
        }
    }

    val searchFieldEmpty by remember(searchTextField.value) {
        derivedStateOf {
            searchTextField.value == ""
        }
    }

    LaunchedEffect(searchFieldEmpty) {
        if (searchFieldEmpty) {
            gridScrollState.animateScrollToItem(0)
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
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val isCollapsed by remember { derivedStateOf { scrollBehavior.state.collapsedFraction > 0.5 } }

    val coroutine = rememberCoroutineScope()


    Scaffold(
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = if (isCollapsed) Color.White else MaterialTheme.colorScheme.primary),
                title = {
                    Text(
                        text = "JetCoins",
                        fontSize = if (isCollapsed) 20.sp else 40.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(),
                        color = if (isCollapsed) Color.Black else Color.White
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize(),
            content = {
                if (screenUiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        SearchTextField(searchTextField)
                        LazyVerticalGrid(
                            state = gridScrollState,
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .pullRefresh(state = pullState),
                            columns = GridCells.Fixed(2),
                            content = {
                                items(
                                    items = searchedCoinList,
                                    key = { it.id }
                                ) { coinModel ->

                                    var currentCoinDetails by remember {
                                        mutableStateOf<CoinDetails?>(null)
                                    }

                                    FlippableCard(
                                        frontSide = {
                                            CoinCard(
                                                coinName = coinModel.name.orEmpty()
                                            )
                                        },
                                        backSide = {
                                            CoinCard(
                                                coinName = coinModel.id,
                                                coinDetails = currentCoinDetails,
                                                isBack = true
                                            )
                                        },
                                        modifier = Modifier.animateItemPlacement(),
                                        onFlipToBack = {
                                            if (currentCoinDetails == null) {
                                                coroutine.launch {
                                                    currentCoinDetails = homeViewModel.getCoinDetail(coinId = coinModel.id)
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        )
                    }
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
fun SearchTextField(
    searchFieldState: MutableState<String>,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchFieldState.value,
        onValueChange = { searchFieldState.value = it },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = "Search coins here",
                fontSize = 16.sp
            )
        },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        shape = CircleShape
    )
}

@Composable
fun CoinCard(
    coinName: String,
    modifier: Modifier = Modifier,
    isBack: Boolean = false,
    coinDetails: CoinDetails? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 200.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(size = 20.dp)
            ),
        contentAlignment = Alignment.Center,
        content = {
            Image(
                painter = painterResource(id = R.drawable.currencyimage),
                contentDescription = null,
                modifier = Modifier.alpha(0.1f),
                contentScale = ContentScale.FillHeight
            )

            if (isBack){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CoinDetailText(title = "Name", value = coinDetails?.name.orEmpty())
                    CoinDetailText(title = "Rank", value = coinDetails?.rank.orEmpty())
                    CoinDetailText(title = "Symbol", value = coinDetails?.symbol.orEmpty())
                    CoinDetailText(title = "Price", value = coinDetails?.price.orEmpty())
                }
            } else {
                Text(
                    text = coinName,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}

@Composable
fun CoinDetailText(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "$title:",
            fontSize = 12.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            fontSize = 16.sp,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
