package br.com.github.sprint3.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.github.sprint3.R

@Composable
fun EurofarmaLogo(
    modifier: Modifier = Modifier,
    size: Dp = 160.dp,
    contentDescription: String? = "Eurofarma Logo"
) {
    Image(
        painter = painterResource(id = R.drawable.eurofarma_logo),
        contentDescription = contentDescription,
        modifier = modifier.size(size)
    )
}

