package com.dicoding.composesubmission.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.composesubmission.data.response.ResultsItem
import com.dicoding.composesubmission.di.Injection
import com.dicoding.composesubmission.ui.common.UiState
import com.dicoding.composesubmission.ui.components.CharacterListItem
import com.dicoding.composesubmission.ui.viewmodel.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                LaunchedEffect(viewModel) {
                    viewModel.getAllCharacter()
                }
            }
            is UiState.Success -> {
                HomeContent(
                    character = uiState.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
            else -> {}
        }
    }
}

@Composable
fun HomeContent(
    character: List<ResultsItem>,
    navigateToDetail: (Int) -> Unit
) {
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(6.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(character, key = {it.id}) {data ->
            CharacterListItem(
                name = data.name ?: "",
                photoUrl = data.image ?: "",
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}
