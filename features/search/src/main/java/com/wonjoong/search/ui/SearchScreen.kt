package com.wonjoong.search.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen() {
    UserInput()
}

@Composable
fun UserInput(
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val userInput = remember {
        mutableStateOf(TextFieldValue())
    }
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (textField, button) = createRefs()
        TextField(
            modifier = Modifier
                .constrainAs(textField) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(button.start)
                }
                .padding(all = 4.dp),
            value = userInput.value,
            onValueChange = { userInput.value = it },
            placeholder = { Text("Find a user by the Github ID") }
        )
        Button(modifier = Modifier
            .constrainAs(button) {
                start.linkTo(textField.end)
                top.linkTo(textField.top)
                bottom.linkTo(textField.bottom)
                end.linkTo(parent.end)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }
            .padding(all = 4.dp),
            onClick = { searchViewModel.searchUser(userInput.value.text) }) {
            Text(text = "Search")
        }
    }
}