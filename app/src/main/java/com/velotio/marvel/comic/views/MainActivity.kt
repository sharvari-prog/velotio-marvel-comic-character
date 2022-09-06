package com.velotio.marvel.comic.views

import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.velotio.marvel.comic.api.CharacterService
import com.velotio.marvel.comic.api.RetrofitHelper
import com.velotio.marvel.comic.models.ResultsItem
import com.velotio.marvel.comic.repository.CharacterRepository
import com.velotio.marvel.comic.viewmodels.MainViewModel
import com.velotio.marvel.comic.viewmodels.MainViewModelFactory
import com.velotio.marvel.comic.views.ui.theme.VelotiosMarvelComicTheme
import java.time.LocalDate
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    private var TAG = "@MainActivity:  "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val characterService =
            RetrofitHelper.getRetrofitInstance().create(CharacterService::class.java)
        val repository = CharacterRepository(characterService)
        var listData: List<ResultsItem> = emptyList()

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.characters.observe(this, Observer {
            listData = it.data.results
            Log.i(TAG, "onCreate: New data 1 ******************** ")
        })



        setContent {
            VelotiosMarvelComicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {


                    CharacterList(resultItems = listData, mainViewModel)

                }
            }
        }
    }
}

@Composable
fun CharacterList(resultItems: List<ResultsItem>, mainViewModel: MainViewModel) {

    Log.e("@@  ", "CharacterList: list data 2 ***********" + resultItems.size)

    val state by mainViewModel.state.collectAsState()

    Log.e("@@@@ ", "CharacterList: state " + state )
    LazyColumn {

//        if (state.isEmpty()) {
//            if (state.isEmpty()) {
//                item {
//                    CircularProgressIndicator(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .wrapContentSize(align = Alignment.Center)
//                    )
//                }
//
//            }
//        }


        itemsIndexed(items = resultItems) { index, item ->
            CharacterListItem(listOfCharacters = item)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    VelotiosMarvelComicTheme {

    }
}