package com.barros9.beerappkmm.android.mainfragment

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.barros9.beerappkmm.android.model.BeerChips
import com.barros9.beerappkmm.android.theme.Shapes
import com.barros9.beerappkmm.android.theme.colorAccent
import com.barros9.beerappkmm.android.theme.colorPrimaryDark
import com.barros9.beerappkmm.android.theme.colorTextDark
import com.barros9.beerappkmm.android.theme.colorTextGrey

@Composable
fun ChipItem(
    chip: BeerChips,
    isSelected: Boolean,
    onExecuteSearch: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(end = 8.dp),
        elevation = 0.dp,
        shape = Shapes.large,
        color = when {
            isSelected -> colorAccent
            else -> colorPrimaryDark
        }
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onExecuteSearch()
                    }
                )
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .widthIn(50.dp),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                text = stringResource(chip.value),
                color = when {
                    isSelected -> colorTextDark
                    else -> colorTextGrey
                }
            )
        }
    }
}
