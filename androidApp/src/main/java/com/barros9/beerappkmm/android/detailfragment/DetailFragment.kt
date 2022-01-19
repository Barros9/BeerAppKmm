package com.barros9.beerappkmm.android.detailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.navArgs
import com.barros9.beerappkmm.android.theme.colorPrimary
import com.barros9.beerappkmm.android.theme.colorTextGrey
import com.barros9.beerappkmm.android.R
import com.barros9.beerappkmm.android.theme.BeerAppTheme
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skydoves.landscapist.glide.GlideImage

class DetailFragment : BottomSheetDialogFragment() {

    private val safeArgs: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                BeerAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = colorPrimary
                    ) {
                        ComposeDetail()
                    }
                }
            }
        }
    }

    @Composable
    fun ComposeDetail() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            GlideImage(
                modifier = Modifier
                    .width(80.dp)
                    .padding(8.dp),
                imageModel = safeArgs.imageUrl ?: "",
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            modifier = Modifier.padding(top = 24.dp),
                            text = safeArgs.name,
                            style = MaterialTheme.typography.h2
                        )
                        Text(
                            text = safeArgs.tagline,
                            style = MaterialTheme.typography.body1,
                            color = colorTextGrey
                        )
                    }
                    Column {
                        Image(
                            painter = painterResource(R.drawable.ic_bookmark),
                            contentDescription = "Bookmark",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(32.dp)
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp, end = 8.dp),
                    text = safeArgs.description,
                    style = MaterialTheme.typography.body2,
                    color = colorTextGrey
                )
            }
        }
    }
}
