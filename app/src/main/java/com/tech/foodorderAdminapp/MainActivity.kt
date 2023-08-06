package com.tech.foodorderAdminapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tech.foodorderAdminapp.navigation.FoodAdminNavigation
import com.tech.foodorderAdminapp.ui.theme.FoodOrderAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodOrderAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FoodAdminNavigation()
                }
            }
        }
    }
}
@Composable
fun GreetingPreview() {
    FoodOrderAppTheme {
        FoodAdminNavigation()
    }
}
