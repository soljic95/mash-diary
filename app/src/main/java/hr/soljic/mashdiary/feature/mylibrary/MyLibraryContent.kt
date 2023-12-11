package hr.soljic.mashdiary.feature.mylibrary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import hr.soljic.mashdiary.R

@Composable
fun MyLibraryContent() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = androidx.appcompat.R.color.material_deep_teal_500)))
}