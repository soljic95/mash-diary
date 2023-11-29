package hr.soljic.mashdiary.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import hr.soljic.mashdiary.R
import hr.soljic.mashdiary.presentation.GoogleButton

@Composable
fun AuthenticationContent(loadingState: Boolean, onActionClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier.weight(5f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxSize(0.25f),
                painter = painterResource(id = R.drawable.google_logo),
                contentDescription = "Google Logo"
            )
            Text(
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                ), text = stringResource(id = R.string.auth_welcome_title)
            )
            Text(
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface
                ), text = stringResource(id = R.string.auth_welcome_subtitle)
            )
        }

        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GoogleButton(loadingState = loadingState,
                modifier = Modifier.fillMaxWidth(0.8f),
                onClick = { onActionClick() })
        }
    }
}


@Preview
@Composable
fun PreviewAuthenticationContent() {
    AuthenticationContent(true) {

    }
}