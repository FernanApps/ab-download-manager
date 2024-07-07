package com.abdownloadmanager.desktop.pages.settings

import com.abdownloadmanager.desktop.pages.settings.configurable.widgets.RenderConfigurable
import com.abdownloadmanager.desktop.ui.WithContentAlpha
import com.abdownloadmanager.desktop.ui.customwindow.WindowIcon
import com.abdownloadmanager.desktop.ui.customwindow.WindowTitle
import com.abdownloadmanager.desktop.ui.icon.IconSource
import com.abdownloadmanager.desktop.ui.icon.MyIcon
import com.abdownloadmanager.desktop.ui.icon.MyIcons
import com.abdownloadmanager.desktop.ui.theme.myColors
import com.abdownloadmanager.desktop.ui.widget.Handle
import com.abdownloadmanager.desktop.ui.widget.Text
import com.abdownloadmanager.desktop.utils.*
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
private fun SideBar(
    settingsComponent: SettingsComponent,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .padding(horizontal = 12.dp)
            .fillMaxHeight()
    ) {
//        var searchText by remember { mutableStateOf("") }
        Spacer(Modifier.height(12.dp))
//        SearchBox(
//            searchText,
//            onTextChange = { searchText = it },
//            modifier = Modifier.height(38.dp),
//        )
//        Spacer(Modifier.height(32.dp))
        for (i in settingsComponent.pages) {
            SideBarItem(
                icon = i.icon,
                name = i.name,
                isSelected = settingsComponent.currentPage == i,
                onClick = {
                    settingsComponent.currentPage = i
                }
            )
        }
    }
}

@Composable
private fun SideBarItem(icon: IconSource, name: String, isSelected: Boolean, onClick: () -> Unit) {
    val shape = RoundedCornerShape(12.dp)
    WithContentAlpha(if (isSelected) 1f else 0.75f) {
        Row(
            Modifier
                .fillMaxWidth()
                .clip(shape)
                .let {
                    if (isSelected) {
                        val selectionColor = myColors.onBackground
                        it
                            .border(
                                1.dp,
                                myColors.selectionGradient(0.10f, 0.05f, selectionColor),
                                shape
                            )
                            .background(myColors.selectionGradient(0.15f, 0f, selectionColor))
                    } else it
                }
                .onClick {
                    onClick()
                }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MyIcon(icon, null, Modifier.size(16.dp))
            Spacer(Modifier.width(8.dp))
            Text(
                name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }


}

@Composable
fun SettingsPage(
    settingsComponent: SettingsComponent,
    onDismissRequest: () -> Unit,
) {
    WindowTitle("Settings")
//    WindowIcon(MyIcons.settings)
    WindowIcon(MyIcons.appIcon)
    Row {
        var sideBarWidth by remember { mutableStateOf(250.dp) }
        SideBar(settingsComponent, Modifier.fillMaxHeight().width(sideBarWidth))
        val currentConfigurables = settingsComponent.configurables
        Handle(
            Modifier.width(5.dp).fillMaxHeight(),
            orientation = Orientation.Horizontal
        ) {
            sideBarWidth = (sideBarWidth + it).coerceIn(150.dp..300.dp)
        }
        AnimatedContent(currentConfigurables) { configurables ->
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                for (cfg in configurables) {
                    Box(
                        Modifier
                            .background(myColors.surface / 50)
                    ) {
                        RenderConfigurable(cfg, Modifier.padding(vertical = 16.dp, horizontal = 32.dp))
                    }
                    Spacer(Modifier.height(1.dp))

//                    Divider()
                }
            }
        }

    }
}



