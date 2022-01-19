package com.barros9.beerappkmm.android.mainfragment

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.barros9.beerappkmm.android.theme.colorAccent
import com.barros9.beerappkmm.android.theme.colorPrimary
import com.barros9.beerappkmm.android.theme.colorPrimaryDark
import com.barros9.beerappkmm.android.theme.colorTextDark
import com.barros9.beerappkmm.android.theme.colorTextGrey
import com.barros9.beerappkmm.android.R
import com.barros9.beerappkmm.android.model.BeerChips
import com.barros9.beerappkmm.android.model.UiState
import com.barros9.beerappkmm.android.theme.BeerAppTheme
import com.barros9.beerappkmm.android.theme.Shapes
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class, ExperimentalComposeUiApi::class)
class MainFragment : Fragment() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                BeerAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = colorPrimary
                    ) {
                        ComposeMain()
                    }
                }
            }
        }
    }

    @Composable
    fun ComposeMain() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Title()
            Search()
            Card()
            Chips()
            List()
        }
    }

    @Composable
    fun Title() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                style = MaterialTheme.typography.h1,
                text = buildAnnotatedString {
                    append(stringResource(R.string.title_beer))
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold)
                    ) {
                        append(stringResource(R.string.title_app))
                    }
                }
            )
        }
    }

    @Composable
    fun Search() {
        val keyboardController = LocalSoftwareKeyboardController.current
        val search: String by mainViewModel.search

        androidx.compose.material.Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            backgroundColor = colorPrimaryDark,
            shape = Shapes.large,
            elevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = colorTextGrey
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.body1,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = colorPrimaryDark,
                        textColor = colorTextGrey
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions {
                        mainViewModel.searchFromBegin()
                        keyboardController?.hide()
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search_text),
                            color = colorTextGrey
                        )
                    },
                    value = search,
                    onValueChange = {
                        mainViewModel.selectedChip.value = null
                        mainViewModel.search.value = it
                        if (search.isBlank()) {
                            keyboardController?.hide()
                            mainViewModel.searchFromBegin()
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun Card() {
        androidx.compose.material.Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            backgroundColor = colorAccent,
            shape = Shapes.large,
            elevation = 0.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.info_card_title),
                        style = MaterialTheme.typography.h2,
                        color = colorTextDark
                    )
                    Text(
                        text = stringResource(R.string.info_card_description),
                        style = MaterialTheme.typography.body1,
                        color = colorTextDark
                    )
                }
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(16.dp),
                    painter = painterResource(R.drawable.ic_beer),
                    contentDescription = "Beer"
                )
            }
        }
    }

    @Composable
    fun Chips() {
        val keyboardController = LocalSoftwareKeyboardController.current
        val scrollState = rememberLazyListState()
        val selectedChip: BeerChips? by mainViewModel.selectedChip

        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp),
            state = scrollState
        ) {
            items(BeerChips.values()) {
                ChipItem(
                    chip = it,
                    isSelected = it == selectedChip,
                    onExecuteSearch = {
                        keyboardController?.hide()
                        if (it == selectedChip) {
                            mainViewModel.selectedChip.value = null
                            mainViewModel.search.value = ""
                            mainViewModel.searchFromBegin()
                        } else {
                            mainViewModel.selectedChip.value = it
                            mainViewModel.search.value = it.name
                            mainViewModel.searchFromBegin()
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun List() {
        val state = rememberLazyListState()
        val result: UiState by mainViewModel.uiState
        val page: Int by mainViewModel.page

        when (result) {
            is UiState.Loading -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(color = colorAccent)
                }
            }
            is UiState.Error -> {
                Toast.makeText(context, R.string.error_text, Toast.LENGTH_SHORT).show()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            mainViewModel.onRetry()
                        },
                        content = {
                            Text(
                                text = stringResource(R.string.retry),
                                style = MaterialTheme.typography.body1,
                                color = colorAccent
                            )
                        }
                    )
                }
            }
            is UiState.Success -> {
                (result as UiState.Success).data.also { beersList ->
                    if (beersList.isEmpty()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .wrapContentSize(Alignment.Center),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body1,
                            text = stringResource(R.string.empty_list_text),
                            color = colorAccent
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .padding(top = 8.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            state = state
                        ) {
                            itemsIndexed(beersList) { index, item ->
                                mainViewModel.onChangeListScrollPosition(index)

                                if ((index + 1) >= (page * PAGE_SIZE)) {
                                    mainViewModel.searchNextPage()
                                }

                                BeerItem(beer = item) { beerItem ->
                                    findNavController().navigate(
                                        R.id.itemListDialogFragment,
                                        bundleOf(
                                            "imageUrl" to beerItem.imageUrl,
                                            "name" to beerItem.name,
                                            "tagline" to beerItem.tagline,
                                            "description" to beerItem.description,
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
