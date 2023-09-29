package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onBackground
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val artworks = listOf(
        R.drawable.a1979, R.drawable.a1982, R.drawable.a1983, R.drawable.a1984, R.drawable.a1985, R.drawable.a1986, R.drawable.a1990, R.drawable.a1992, R.drawable.a1997, R.drawable.a2000, R.drawable.a2001, R.drawable.a2002, R.drawable.a2008, R.drawable.a2019, R.drawable.a2020
    )
    val titles = listOf(
        R.string.a1979, R.string.a1982, R.string.a1983, R.string.a1984, R.string.a1985, R.string.a1986, R.string.a1990, R.string.a1992, R.string.a1997, R.string.a2000, R.string.a2001, R.string.a2002, R.string.a2008, R.string.a2019, R.string.a2020
    )

    val description = listOf(
        R.string.a1979_text, R.string.a1982_text, R.string.a1983_text, R.string.a1984_text, R.string.a1985_text, R.string.a1986_text, R.string.a1990_text, R.string.a1992_text, R.string.a1997_text, R.string.a2000_text, R.string.a2001_text, R.string.a2002_text, R.string.a2008_text, R.string.a2019_text, R.string.a2020_text
    )



    var currentIndex by remember { mutableStateOf(0) }
    var currentArtwork by remember { mutableStateOf(artworks[0]) }
    var title by remember { mutableStateOf(titles[0]) }
    var texto_serie by remember { mutableStateOf(description[0]) }

    fun changeArtwork(index: Int) {
        currentArtwork = artworks[index]
        title = titles[index]
        texto_serie = description[index]
    }

    var restartButtonPressed by remember { mutableStateOf(false) }

    DisposableEffect(restartButtonPressed) {
        if (restartButtonPressed) {
            restartButtonPressed = false
        }
        onDispose { }
    }

    fun onButtonClick(isNext: Boolean) {
        val newIndex = if (isNext) (currentIndex + 1) % artworks.size else (currentIndex - 1 + artworks.size) % artworks.size
        currentIndex = newIndex
        changeArtwork(newIndex)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.size(16.dp))
        Text(text = "Marlon Andres Anacona", fontSize = 20.sp, color = Color.Red)
        Spacer(modifier = modifier.size(16.dp))
        Text(text = " 2023777 ", fontSize = 20.sp, color = Color.Cyan)
        Spacer(modifier = modifier.size(16.dp))
        Text(text = "America de Cali  championship titles", fontSize = 20.sp, color = Color.Red)
        Spacer(modifier = modifier.size(16.dp))
        ArtworkDisplay(currentArtwork = currentArtwork)
        Spacer(modifier = modifier.size(16.dp))
        ArtworkTitle(title = title, texto_serie = texto_serie)
        Spacer(modifier = modifier.size(25.dp))
        Row(
            modifier = modifier.padding(horizontal = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        currentIndex = (currentIndex - 1 + artworks.size) % artworks.size
                        changeArtwork(currentIndex)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow
                    )
                ) {
                    Text(
                        text = "Next  championship titles",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black)
                    )
                }

                Button(
                    onClick = {
                        currentIndex = (currentIndex + 1) % artworks.size
                        changeArtwork(currentIndex)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.teal_200)
                    )
                ) {
                    Text(
                        text = "Next  championship titles",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black)
                    )
                }
            }

            IconButton(
                onClick = {
                    restartButtonPressed = true
                    currentIndex = 0
                    changeArtwork(0)
                },
                modifier = Modifier.size(60.dp),
                content = {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Restart",
                        tint = Color.Red
                    )
                }
            )

        }
    }
}

@Composable
fun ArtworkDisplay(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(currentArtwork),
            contentDescription = stringResource(id = R.string.a1979),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(15.dp))
        )
    }
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes texto_serie: Int
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.purple_200),
            fontSize = 32.sp
        )

        Text(
            text = "— ${stringResource(id = texto_serie)} —",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.black)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}