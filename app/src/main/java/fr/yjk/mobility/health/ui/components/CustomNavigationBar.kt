package fr.yjk.mobility.health.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults.windowInsets
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.yjk.mobility.health.ui.screens.workspace.BottomNavigationBarItem

@Composable
fun CustomNavigationBar(content: @Composable RowScope.() -> Unit) {
    Surface(
        modifier = Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ),
        color = if (isSystemInDarkTheme()) Color.Black else Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .windowInsetsPadding(windowInsets)
                .defaultMinSize(minHeight = 80.0.dp)
                .selectableGroup(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}

@Composable
fun CustomNavigationBarItem(modifier: Modifier = Modifier, index: Int, item: BottomNavigationBarItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .height(height = 48.dp)
            .background(
                color = if (index == item.index) MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.1f
                ) else Color.Unspecified
            ),
    ) {
        Icon(
            painter = painterResource(id = if (index == item.index) item.resourceIconUnselected else item.resourceIconSelected),
            contentDescription = stringResource(id = item.resourceLabel),
            modifier = Modifier.size(size = 24.dp),
            tint = if (index == item.index || index < 4 && index + 1 == item.index || index == 4 && index - 1 == item.index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(width = 4.dp))
        AnimatedVisibility(index == item.index) {
            Text(
                stringResource(id = item.resourceLabel),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }

    }
}
