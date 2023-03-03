package com.example.assignment

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment.data.api.model.MockData
import kotlin.math.roundToInt

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
//    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
//    val state by homeViewModel.state.collectAsState()
    val threshold = 14
    Column(modifier = Modifier.fillMaxSize()) {
        Divider(thickness = 2.dp, color = Color.Black)
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            // content padding
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 12.dp,
                top = 8.dp,
                bottom = 8.dp
            ),
            // content spacing
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = MockData.recordList.filter { it.pm2_5!! <= threshold }) { item ->
                LazyRowViewItem(
                    siteId = item.sitedId ?: 0,
                    siteName = item.siteName ?: "",
                    county = item.county ?: "",
                    pm2_5 = item.pm2_5 ?: 0,
                    status = item.status ?: ""
                )
            }
        }
        Divider(thickness = 2.dp, color = Color.Black)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = MockData.recordList.filter { it.pm2_5!! > threshold }) { item ->
                LazyColumnViewItem(
                    siteId = item.sitedId ?: 0,
                    siteName = item.siteName ?: "",
                    county = item.county ?: "",
                    pm2_5 = item.pm2_5 ?: 0,
                    status = item.status ?: ""
                )
            }
        }
    }

}

@Composable
fun LazyRowViewItem(
    siteId: Int,
    siteName: String,
    county: String,
    pm2_5: Int,
    status: String
) {
    Surface(
        modifier = Modifier,
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Column(
            modifier = Modifier.defaultMinSize(minWidth = 60.dp).padding(vertical = 8.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = siteId.toString(), fontSize = 12.sp)
                Text(text = siteName, fontSize = 12.sp)
                Text(text = pm2_5.toString(), fontSize = 12.sp)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = county, fontSize = 12.sp)
                Text(text = status, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun LazyColumnViewItem(
    siteId: Int,
    siteName: String,
    county: String,
    pm2_5: Int,
    status: String
) {
    val mContext = LocalContext.current

    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .defaultMinSize(minHeight = 48.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.defaultMinSize(minWidth = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = siteId.toString(), fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1.0f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = siteName, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(text = county, fontSize = 12.sp)
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(text = pm2_5.toString(), fontSize = 12.sp)
            Text(
                modifier = Modifier.maxWidth(fraction = 0.5f),
                text = if (status == stringResource(id = R.string.status_good)) stringResource(id = R.string.have_fun) else status,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (status != stringResource(id = R.string.status_good)) {
            IconButton(onClick = { showToast(mContext) }) {
                Icon(
                    Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Toast"
                )
            }
        } else {
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
    Divider(modifier = Modifier.padding(end = 16.dp), startIndent = 16.dp, thickness = 1.dp, color = Color.Black)
}

private fun showToast(context: Context){
    Toast.makeText(context, "This is a Sample Toast", Toast.LENGTH_LONG).show()
}

fun Modifier.maxWidth(
    fraction: Float = 1f,
) = layout { measurable, constraints ->
    val maxWidth = (constraints.maxWidth * fraction).roundToInt()
    val width = measurable.maxIntrinsicWidth(constraints.maxHeight).coerceAtMost(maxWidth)

    val placeable = measurable.measure(Constraints(constraints.minWidth, width, constraints.minHeight, constraints.maxHeight))
    layout(width, placeable.height) {
        placeable.placeRelative(0, 0)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}