package com.velotio.marvel.comic.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.velotio.marvel.comic.models.ResultsItem

@Composable
fun CharacterListItem(listOfCharacters: ResultsItem){


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
                            data = "https://www.bing.com/images/search?view=detailV2&ccid=ssZfUI8%2f&id=D05A5411EA64B4C5CC9773045578A3B353C01D28&thid=OIP.ssZfUI8_yehzacZnKlhY0QHaEo&mediaurl=https%3a%2f%2fth.bing.com%2fth%2fid%2fR.b2c65f508f3fc9e87369c6672a5858d1%3frik%3dKB3AU7OjeFUEcw%26riu%3dhttp%253a%252f%252fthewowstyle.com%252fwp-content%252fuploads%252f2015%252f01%252fnature-images-wallpaper.jpg%26ehk%3dlp1xoHZosfpvmPbYdSLC8L9mf7bjvSI40TTBCnsonHM%253d%26risl%3d%26pid%3dImgRaw%26r%3d0&exph=1600&expw=2560&q=images&simid=608005307576776425&FORM=IRPRST&ck=B219E913D3F632920005A34D90413CAD&selectedIndex=1&ajaxhist=0&ajaxserp=0",

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