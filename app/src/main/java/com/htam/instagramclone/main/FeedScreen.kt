package com.htam.instagramclone.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.htam.instagramclone.IgViewModel

@Composable
fun FeedScreen(navController: NavController, vm: IgViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Feed Screen")
        }
        BottomNavigationMenu(selectedIte = BottomNavigationItem.FEED, navController = navController)
    }
}