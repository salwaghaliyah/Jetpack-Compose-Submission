package com.dicoding.composesubmission.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.composesubmission.ui.theme.ComposeSubmissionTheme


@Composable
fun CharacterListItem(
    modifier: Modifier = Modifier,
    name: String,
    photoUrl: String,
) {
    Surface (
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
        ){
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(6.dp)
                    .size(85.dp)
                    .clip(CircleShape)
            )
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 16.dp)
            )
        }
    }
//    Card(
//        shape = RoundedCornerShape(12.dp),
//        modifier = modifier
//            .padding(0.5.dp),
//        elevation = CardDefaults.cardElevation(8.dp),
//    ) {
//
//    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListItemPreview() {
    ComposeSubmissionTheme {
        CharacterListItem(name = "RICKKK",
            photoUrl = "https://www.diamondartclub.com/cdn/shop/products/rick-and-morty-diamond-art-painting-33240468914369.jpg?v=1667779267&width=3000",
        )
    }
}