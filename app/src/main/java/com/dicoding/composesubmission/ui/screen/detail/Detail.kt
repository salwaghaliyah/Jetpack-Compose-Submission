package com.dicoding.composesubmission.ui.screen.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.dicoding.composesubmission.R
import com.dicoding.composesubmission.di.Injection
import com.dicoding.composesubmission.ui.common.UiState
import com.dicoding.composesubmission.ui.components.CardDetail
import com.dicoding.composesubmission.ui.theme.ComposeSubmissionTheme
import com.dicoding.composesubmission.ui.viewmodel.ViewModelFactory

@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {uiState ->
        when(uiState) {
            is UiState.Loading -> {
                viewModel.getCharacterById(id)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    image = data.image ?: "",
                    name = data.name ?: "Unknown",
                    species = data.species ?: "Unknown",
                    location = data.location?.name?.split(" ")?.firstOrNull() ?: "Unknown",
                    gender = data.gender ?: "Unknown",
                    status = data.status ?: "Unknown",
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {
                Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun DetailContent(
    image: String,
    name: String,
    species: String,
    location: String,
    gender: String,
    status: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ){
            Card(
                modifier = Modifier
                    .height(350.dp)
                    .width(350.dp)
                    .padding(16.dp)
                    .shadow(2.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
            Text(
                text = name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp
                ),
            )

        }
        Column (
            modifier = Modifier
                .padding(16.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                CardDetail(
                    title = stringResource(R.string.title_species),
                    value = species,
                    modifier = Modifier.weight(1f)
                )
                CardDetail(
                    title = stringResource(R.string.title_location),
                    value = location,
                    modifier = Modifier.weight(1f)
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                CardDetail(
                    title = stringResource(R.string.title_gender),
                    value = gender,
                    modifier = Modifier.weight(1f)
                )
                CardDetail(
                    title = stringResource(R.string.title_status),
                    value = status,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    ComposeSubmissionTheme {
        DetailContent(
            image = "https://www.diamondartclub.com/cdn/shop/products/rick-and-morty-diamond-art-painting-33240468914369.jpg?v=1667779267&width=3000",
            name = "Trunk Man",
            species = "Humanoid",
            location = "Trunk-Person",
            gender = "Male",
            status = "Alive",
            onBackClick = { }
        )
    }
}