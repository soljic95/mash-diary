package hr.soljic.mashdiary.feature.authentication.presentation.sign_in

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import hr.soljic.mashdiary.R

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    userSignedIn: Boolean = false,
    primaryText: String = stringResource(id = R.string.auth_primary_text),
    secondaryText: String = stringResource(id = R.string.auth_secondary_text),
    icon: Int = R.drawable.google_logo,
    shape: CornerBasedShape = Shapes().extraSmall,
    borderColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    borderStrokeWidth: Dp = 1.dp,
    onClick: () -> Unit,
) {
    var buttonText by remember { mutableStateOf(primaryText) }

    LaunchedEffect(key1 = userSignedIn) {
        buttonText = if (userSignedIn) secondaryText else primaryText
    }

    Surface(
        modifier = modifier.clickable(!userSignedIn) {
            onClick()
        },
        shape = shape,
        border = BorderStroke(borderStrokeWidth, color = borderColor),
        color = backgroundColor
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300, easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (!userSignedIn) {
                Image(painter = painterResource(id = icon), contentDescription = "Google Logo")
            }
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = buttonText,
                style = TextStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize)
            )

        }

    }


}

@Preview
@Composable
fun GoogleButtonPreview() {
    GoogleButton {

    }
}


@Preview
@Composable
fun GoogleButtonPreview2() {
    GoogleButton(userSignedIn = true) {

    }
}