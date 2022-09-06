package com.velotio.marvel.comic.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.velotio.marvel.comic.models.ResultData
import com.velotio.marvel.comic.models.ResultsItem
import com.velotio.marvel.comic.viewmodels.CharacterViewModel
import com.velotio.marvel.comic.views.ui.theme.VelotiosMarvelComicTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel by viewModels<CharacterViewModel>()


        setContent {
            VelotiosMarvelComicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    listData(characterList = mainViewModel.getCharacterList())

                }
            }
        }
    }
}

@Composable
fun listData(characterList: List<ResultData>) {


    LazyColumn {
        itemsIndexed(items = characterList) { index, item ->
            CharacterListItem(listOfCharacters = item)
        }
    }
}






@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    VelotiosMarvelComicTheme {
        Greeting("Android")
    }
}