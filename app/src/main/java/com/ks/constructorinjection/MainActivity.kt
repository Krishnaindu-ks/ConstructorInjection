package com.ks.constructorinjection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
class UserRepository(private val username:String){
    fun getUser():String {
        return "User: $username"
    }
}
class MainViewModel(private val userRepository: UserRepository){
    fun getUser():String {
        return userRepository.getUser()
    }
}
@Composable
fun MainScreen(viewModel: MainViewModel, modifier: Modifier = Modifier){
    val user = viewModel.getUser()
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = user)
        Button(onClick = { /*TODO*/ }) {
            Text("Welcome")

        }
    }
}
@Preview
@Composable
fun MainScreenPreview() {
    val userRepository = UserRepository("Constructor Injection")
    val viewModel = MainViewModel(userRepository)
    MainScreen(viewModel)
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRepository = UserRepository("Constructor Injection")
        val viewModel = MainViewModel(userRepository)
        val composeView = ComposeView(this)
        composeView.setContent {
            MainScreen(viewModel)
        }
        setContentView(composeView)
    }
}
