package com.velotio.marvel.comic.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.velotio.marvel.comic.models.ResultData
import com.velotio.marvel.comic.models.ResultsItem
import com.velotio.marvel.comic.views.ui.theme.VelotiosMarvelComicTheme

@Composable
fun CharacterListItem(listOfCharacters: ResultData){


//    VelotiosMarvelComicTheme{
        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth()
                .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
        ) {
            Surface() {


                Row(
                    Modifier
                        .padding(4.dp)
                        .fillMaxSize()
                ) {

                    Image(
                        painter = rememberImagePainter(
                            data = listOfCharacters.imageUrl,

                            builder = {
                                scale(coil.size.Scale.FILL)
                                placeholder(coil.compose.base.R.drawable.notification_action_background)
                                transformations(CircleCropTransformation())

                            }
                        ),
                        contentDescription = listOfCharacters.description,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.2f)
                    )


                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxHeight()
                            .weight(0.8f)
                    ) {
                        listOfCharacters.name?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
//                        Text(
//                            text = listOfCharacters.description,
//                            style = MaterialTheme.typography.caption,
//                            modifier = Modifier
//                                .background(
//                                    Color.LightGray
//                                )
//                                .padding(4.dp)
//                        )
//                    Text(
//                        text = listOfCharacters.id.toString(),
//                        style = MaterialTheme.typography.body1,
//                        maxLines = 2,
//                        overflow = TextOverflow.Ellipsis
//                    )

                    }
                }
            }
//        }
    }



}