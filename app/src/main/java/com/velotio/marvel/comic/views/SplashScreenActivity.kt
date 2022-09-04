package com.velotio.marvel.comic.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.tooling.preview.Preview
import com.velotio.marvel.comic.R
import com.velotio.marvel.comic.views.ui.theme.VelotiosMarvelComicTheme

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VelotiosMarvelComicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    color = MaterialTheme.colors.background
                ) {
                    Img()
                }
            }
        }
    }
}

@Composable
fun Img() {
    Image(
        painter = painterResource(id = R.drawable.img_activity_splash_screen),
        contentDescription = "Splash Screen",
        modifier = Modifier.fillMaxWidth()
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VelotiosMarvelComicTheme {
        Img()
    }
}