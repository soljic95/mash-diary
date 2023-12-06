package hr.soljic.mashdiary.feature.authentication.presentation.sign_in

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.soljic.mashdiary.R

@Composable
fun AuthenticationContent(signInState: SignInState, onActionClick: () -> Unit) {

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

            Spacer(modifier = Modifier.padding(top = 8.dp))

            if (signInState.loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .wrapContentHeight()

                )
            }
        }

        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GoogleButton(userSignedIn = false,
                modifier = Modifier.fillMaxWidth(0.8f),
                onClick = { onActionClick() })
        }


    }


}


@Preview
@Composable
fun PreviewAuthenticationContent() {
    AuthenticationContent(SignInState(loading = true), { })
}