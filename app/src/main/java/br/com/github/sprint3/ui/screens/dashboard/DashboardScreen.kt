package br.com.github.sprint3.ui.screens.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.github.sprint3.ui.components.AppBottomNavigation
import br.com.github.sprint3.ui.components.BottomTab
import br.com.github.sprint3.ui.screens.home.DashboardTabContent
import br.com.github.sprint3.ui.screens.home.HomeTopBar

@Composable
fun DashboardScreen(
    onTabSelected: (BottomTab) -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            HomeTopBar(onLogoutClick = onLogoutClick)
        },
        bottomBar = {
            AppBottomNavigation(
                currentTab = BottomTab.DASHBOARD,
                onTabSelected = onTabSelected
            )
        },
        containerColor = Color(0xFFF8F9FA)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            DashboardTabContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}
