package remote.lunar.remotedrive.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithSearch(
    searchText: TextFieldValue,
    onSearchTextChanged: (TextFieldValue) -> Unit,
    onDrawerIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            TextField(
                value = searchText,
                onValueChange = onSearchTextChanged,
                placeholder = { Text(text = "Search...") },
                leadingIcon = {
                    IconButton(onClick = onDrawerIconClick) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Drawer Icon")
                    }
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More Icon",
                        modifier = Modifier
                            .background(Color.LightGray, shape = CircleShape)
                            .padding(4.dp)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    // backgroundColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = onDrawerIconClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Drawer Icon")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Add action */ }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Icon")
            }
        }
    )
}

@Preview
@Composable
fun TopAppBarWithSearchPreview() {
    TopAppBarWithSearch(
        searchText = TextFieldValue(),
        onSearchTextChanged = {},
        onDrawerIconClick = {}
    )
}
