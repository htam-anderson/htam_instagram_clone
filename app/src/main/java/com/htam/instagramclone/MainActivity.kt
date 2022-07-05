package com.htam.instagramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.htam.instagramclone.auth.LoginScreen
import com.htam.instagramclone.auth.ProfileScreen
import com.htam.instagramclone.auth.SignupScreen
import com.htam.instagramclone.main.*
import com.htam.instagramclone.ui.theme.InstagramCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    InstagramApp()
                }
            }
        }
    }
}

sealed class DestinationScreen(val route: String) {
    object Signup : DestinationScreen("signup")
    object Login : DestinationScreen("login")
    object Feed : DestinationScreen("feed")
    object Search : DestinationScreen("search")
    object MyPosts : DestinationScreen("mypost")
    object Profile : DestinationScreen("profile")
    object NewPost : DestinationScreen("newpost/{imageUri}") {
        fun createRoute(uri: String) = "newpost/$uri"
    }
}

@Composable
fun InstagramApp() {
    val vm = hiltViewModel<IgViewModel>()
    val navController = rememberNavController()

    NotificationMessage(vm = vm)

    NavHost(navController = navController, startDestination = DestinationScreen.Signup.route) {
        composable(DestinationScreen.Signup.route) {
            SignupScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Login.route) {
            LoginScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Feed.route) {
            FeedScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Search.route) {
            SearchScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.MyPosts.route) {
            MyPostsScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.Profile.route) {
            ProfileScreen(navController = navController, vm = vm)
        }
        composable(DestinationScreen.NewPost.route) { navBackStackEntry ->
            val imageUri = navBackStackEntry.arguments?.getString("imageUri")
            imageUri?.let { it ->
                NewPostScreen(navController = navController, vm = vm, encodedUri = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InstagramCloneTheme {
        InstagramApp()
    }
}