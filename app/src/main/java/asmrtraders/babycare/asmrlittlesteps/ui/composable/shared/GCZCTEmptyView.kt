package asmrtraders.babycare.asmrlittlesteps.ui.composable.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GCZCTEmptyView(
    modifier: Modifier = Modifier,
    primaryText: String,
    secondaryText: String? = null,
    icon: Painter? = null,
    iconContentDescription: String? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        icon?.let {
            Image(
                painter = it,
                contentDescription = iconContentDescription,
            )
        }

        Text(
            text = primaryText,
            modifier = Modifier.padding(horizontal = 6.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )

        secondaryText?.let {
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = it,
                modifier = Modifier.padding(horizontal = 12.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}