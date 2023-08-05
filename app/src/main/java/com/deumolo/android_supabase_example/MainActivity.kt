package com.deumolo.android_supabase_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.deumolo.android_supabase_example.ui.theme.AndroidsupabaseexampleTheme
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidsupabaseexampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        getData()
    }

    private fun getData(){
        lifecycleScope.launch {
            val client = getClient()
            val supabaseResponse = client.postgrest["users"].select()
            val data = supabaseResponse.decodeList<User>()
            println("Supabase Data: " + data)
        }
    }

    private fun getClient(): SupabaseClient {
        return createSupabaseClient(supabaseUrl ="https://kodfvjtjbdmffllfslyu.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtvZGZ2anRqYmRtZmZsbGZzbHl1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODkzODkwOTgsImV4cCI6MjAwNDk2NTA5OH0.OJeKMAKuEKZ8rV_Xp243ofttqyVEFPfUDrnSs7E_4Cw") {
            install(Postgrest)
        }
    }

}
@kotlinx.serialization.Serializable
data class User (
    val id : Int = 0,
    val first_name : String = "",
    val last_name : String = "",
    val email : String = "",
)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidsupabaseexampleTheme {
        Greeting("Android")
    }
}