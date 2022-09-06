package com.velotio.marvel.comic.views
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.velotio.marvel.comic.models.ResultsItem
import com.velotio.marvel.comic.viewmodels.MainViewModel

@Composable
fun HomeScreen() {
    val homeViewModel = viewModel(modelClass = MainViewModel::class.java)
    val state by homeViewModel.state.collectAsState()

    LazyColumn {
        if (state.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

        }

//        items(state) { resultsItem: ResultsItem ->
//            CharacterImageCard(resultsItem = resultsItem)
//        }


    }

}

@Composable
fun CharacterImageCard(resultsItem: ResultsItem) {
    val imagerPainter = rememberImagePainter(data = resultsItem.thumbnail.path+"."+resultsItem.thumbnail.extension)

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp)
    ) {
        Box {

            Image(
                painter = imagerPainter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )

            Surface(
                color = MaterialTheme.colors.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = "Real name: ${resultsItem.name}")
                    Text(text = "Actor name: ${resultsItem.description}")
                }
            }


        }


    }


}