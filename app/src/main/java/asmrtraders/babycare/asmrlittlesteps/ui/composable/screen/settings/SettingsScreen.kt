package asmrtraders.babycare.asmrlittlesteps.ui.composable.screen.settings
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)
        Text("About", color = MaterialTheme.colorScheme.primary)
        Card {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text("ASMRLittle Steps", style = MaterialTheme.typography.titleLarge)
                Text("ASMR TRADERS LIMITED")
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                Text("Version 1.0")
            }
        }
        Text("Support & legal", color = MaterialTheme.colorScheme.primary)
        OutlinedButton(
            onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://asmrtraders.surf/"))) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(Icons.Default.OpenInNew, contentDescription = null)
            Text("Customer Support", modifier = Modifier.padding(start = 8.dp))
        }
        Text(
            "Customer support opens the company website. Product availability and collection details are confirmed by the store.",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
