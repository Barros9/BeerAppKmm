package com.barros9.beerappkmm.android.mainfragment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.barros9.beerappkmm.domain.model.Beer
import com.barros9.beerappkmm.android.theme.colorAccent
import com.barros9.beerappkmm.android.theme.colorPrimaryDark
import com.barros9.beerappkmm.android.theme.colorText
import com.barros9.beerappkmm.android.theme.colorTextGrey
import com.barros9.beerappkmm.android.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BeerItem(beer: Beer, onBeerClick: (Beer) -> Unit = {}) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onBeerClick(beer) })
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier
                .width(40.dp)
                .wrapContentHeight(),
            imageModel = beer.imageUrl ?: "",
            contentScale = ContentScale.Fit,
            placeHolder = ImageVector.vectorResource(R.drawable.ic_loading),
            error = ImageVector.vectorResource(R.drawable.ic_broken_image)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = beer.name,
                style = MaterialTheme.typography.body1,
                color = colorText
            )
            Text(
                text = beer.tagline,
                style = MaterialTheme.typography.body2,
                color = colorTextGrey
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = beer.description,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = colorTextGrey
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                text = stringResource(R.string.more_info),
                style = MaterialTheme.typography.body2,
                color = colorAccent
            )
        }
    }
    Divider(color = colorPrimaryDark, thickness = 1.dp)
}
