package com.example.assignment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onBackButtonClick: () -> Unit = {},
) {
    var textInput by remember { mutableStateOf("") }
    val isSearching by remember { viewModel.isSearching }
    val recordList = viewModel.records.value

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            input = textInput,
            onInputChange = { textInput = it },
            hint = stringResource(id = R.string.search_bar_hint),
            onBackClick = onBackButtonClick,
            onSearch = { viewModel.searchByRegion(it) }
        )
        Divider(thickness = 2.dp, color = Color.Black)
        if (!isSearching) {
            BoxView(text = stringResource(id = R.string.search_view_hint))
        } else {
            if(recordList.isEmpty()) {
                BoxView(text = stringResource(id = R.string.search_not_found_hint, textInput))
            } else {
                VerticalScrollableRecordList(
                    modifier = Modifier.fillMaxWidth(),
                    recordList = recordList,
                    condition = { true }
                )
            }
        }
    }
}

@Composable
fun BoxView(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, textAlign = TextAlign.Center)
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    input: String,
    onInputChange: (String) -> Unit,
    hint: String,
    onBackClick: () -> Unit = {},
    onSearch: (String) -> Unit = {},
) {
    Box(modifier = modifier) {
        TextField(
            modifier = modifier,
            value = input,
            onValueChange = {
                onInputChange(it)
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
            placeholder = { Text(text = hint) },
            leadingIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(viewModel = HomeViewModel())
}