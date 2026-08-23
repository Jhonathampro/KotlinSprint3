package br.com.github.sprint3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.github.sprint3.ui.theme.EuroBlue
import br.com.github.sprint3.ui.theme.EuroYellow

enum class BottomTab {
    HOME,
    TURMAS,
    PLATAFORMAS,
    DASHBOARD
}

@Composable
fun AppBottomNavigation(
    currentTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(EuroBlue)
            .padding(vertical = 8.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            label = "Home",
            isSelected = currentTab == BottomTab.HOME,
            onClick = { onTabSelected(BottomTab.HOME) }
        )
        BottomNavItem(
            label = "Turmas",
            isSelected = currentTab == BottomTab.TURMAS,
            onClick = { onTabSelected(BottomTab.TURMAS) }
        )
        BottomNavItem(
            label = "Plataformas",
            isSelected = currentTab == BottomTab.PLATAFORMAS,
            onClick = { onTabSelected(BottomTab.PLATAFORMAS) }
        )
        BottomNavItem(
            label = "Dashboard",
            isSelected = currentTab == BottomTab.DASHBOARD,
            onClick = { onTabSelected(BottomTab.DASHBOARD) }
        )
    }
}

@Composable
private fun BottomNavItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 2.dp)
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(28.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(EuroYellow),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = label,
                    tint = EuroBlue,
                    modifier = Modifier.size(18.dp)
                )
            }
        } else {
            Box(
                modifier = Modifier.size(28.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = label,
                    tint = EuroYellow,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            color = EuroYellow,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
