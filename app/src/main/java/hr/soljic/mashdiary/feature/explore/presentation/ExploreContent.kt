package hr.soljic.mashdiary.feature.explore.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import hr.soljic.mashdiary.core.Response
import hr.soljic.mashdiary.feature.explore.domain.ExploreViewModel
import hr.soljic.mashdiary.feature.explore.domain.model.ExploreItemModel
import hr.soljic.mashdiary.feature.explore.domain.model.FeaturedModel
import hr.soljic.mashdiary.feature.home.navigation.HomeScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreContent(navController: NavController) {
    val viewModel: ExploreViewModel = hiltViewModel()
    val featuredItems = viewModel.featuredResponse.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 56.dp, bottom = 56.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {

        when (featuredItems.value) {
            is Response.Error -> {} //set error composable
            is Response.Loading -> {} //set load composable
            is Response.Success -> {
                Spacer(modifier = Modifier.size(24.dp))
                HorizontalItemContainer(
                    featuredItems = featuredItems.value.data,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun HorizontalItemContainer(featuredItems: List<FeaturedModel>?, navController: NavController) {
    featuredItems?.forEach {
        Text(text = it.title, modifier = Modifier.padding(start = 24.dp), fontSize = 24.sp)
        LazyRow(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            items(it.data) {
                HorizontalItem(item = it, navController = navController)
            }
        }
        Spacer(modifier = Modifier.size(24.dp))
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Something went wrong")
        }
    }
}

@Composable
fun HorizontalItem(
    item: ExploreItemModel,
    navController: NavController
) {

    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder
            (LocalContext.current)
            .data(item.coverPhoto)
            .allowConversionToBitmap(enable = true)
            .size(200,300)
            .build()
    ).state

    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    val dominantColor by remember {
        mutableStateOf(defaultColor)
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        dominantColor,
                        Color.Cyan,
                    )
                )
            )
            .fillMaxHeight()
            .clickable {
                navController.navigate(HomeScreens.Details.route)
            },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (imageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .fillMaxHeight(0.3f)
                    .padding(6.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = item.title
                )
            }
        }

        if (imageState is AsyncImagePainter.State.Success) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(6.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .fillMaxSize(0.7f)
                    .aspectRatio(9f / 12f),
                painter = imageState.painter,
                contentDescription = item.title,
                contentScale = ContentScale.Crop
            )
        }







        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = item.title, maxLines = 2, modifier = Modifier
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = item.dateOfRelease, modifier = Modifier
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
    }

}

@Preview
@Composable
fun preview() {
    //ExploreContent()
    //HorizontalItemContainer()
    // HorizontalItem()
}

