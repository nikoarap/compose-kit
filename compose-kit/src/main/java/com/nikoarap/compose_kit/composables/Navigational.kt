package com.nikoarap.compose_kit.composables

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.models.BottomAppBarAction
import com.nikoarap.compose_kit.models.NavBottomItem
import com.nikoarap.compose_kit.models.NavDrawerItem
import com.nikoarap.compose_kit.models.TabItem
import com.nikoarap.compose_kit.styles.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.ICON
import com.nikoarap.compose_kit.utils.Constants.Companion.TEN
import com.nikoarap.compose_kit.utils.LayoutUtils
import kotlinx.coroutines.launch

/**
 * A composable that represents a navigation page with a top app bar, a navigation drawer, a bottom navigation bar, and a floating action button.
 * You can create a list of [NavDrawerItem] objects to populate the navigation drawer and a list of [NavBottomItem] objects to populate the bottom NavigationBar,
 * with each containing the customizable properties along with their lambda functions in order for this composable to be highly customizable and reusable.
 *
 * @param topBarTitle                       The title text for the top app bar.
 * @param topBarTitleToCenter               Whether to center-align the title in the top app bar.
 * @param topBarTitleTypography             The typography for the top app bar title.
 * @param topBarColor                       The background color of the top app bar.
 * @param topBarTitleColor                  The color of the top app bar title.
 * @param drawerOpenIconResName             The resource name for the drawer open icon.
 * @param drawerOpenIconTintColor           The tint color for the drawer open icon.
 * @param topBarEndIconResName              The resource name for the top app bar's end icon.
 * @param topBarEndIconTintColor            The tint color for the top app bar's end icon.
 * @param drawerContainerColor              The background color for the navigation drawer.
 * @param drawerTitle                       The title text for the navigation drawer.
 * @param drawerTitleColor                  The color of the navigation drawer title.
 * @param drawerTitleTypography             The typography for the navigation drawer title.
 * @param navDrawerItems                    A list of navigation drawer items.
 * @param navBottomItems                    A list of bottom navigation items.
 * @param bottomBarContainerColor           The background color of the bottom app bar.
 * @param selectedBottomBarItemIndex        The index of the selected bottom navigation item.
 * @param fabIconResName                    The resource name for the floating action button icon.
 * @param fabBackgroundColor                The background color of the floating action button.
 * @param fabIconTintColor                  The tint color for the floating action button icon.
 * @param onTopBarEndIconClicked            The lambda to be invoked when the top app bar's end icon is clicked.
 * @param onFabClicked                      The lambda to be invoked when the floating action button is clicked.
 * @param screenContent                     The content of the navigation page, displayed in the main area.
 *
 * @sample
 * ```
 *  val primaryColor = Color(0xFF6495ED)
 *  val secondaryColor = Color(0xff381E72)
 *
 *  val toast1 = Toast.makeText(this, "Toast 1 Test", Toast.LENGTH_SHORT)
 *  val toast2 = Toast.makeText(this, "Toast 2 Test", Toast.LENGTH_SHORT)
 *  val toast3 = Toast.makeText(this, "Toast 3 Test", Toast.LENGTH_SHORT)
 *  val toast4 = Toast.makeText(this, "Toast 4 Test", Toast.LENGTH_SHORT)
 *
 *  val navDrawerItems = listOf(
 *             NavDrawerItem(label = "Home", iconResName = "ic_home", onClick = {toast1.show()}),
 *             NavDrawerItem(label = "Settings", iconResName = "ic_settings", onClick = {toast2.show()}),
 *             NavDrawerItem(label = "Search", iconResName = "ic_search", onClick = {toast3.show()}),
 *             NavDrawerItem(label = "Add", iconResName = "ic_add", onClick = {toast4.show()}),
 *         )
 *
 *  val navBottomItems = listOf(
 *             NavBottomItem(order = 0, label = "Home", iconResName = "ic_add", tintColor = Color.White, selectedTintColor = secondaryColor, onSelected = { toast1.show() }),
 *             NavBottomItem(order = 1, label = "Search", iconResName = "ic_search", tintColor = Color.White, selectedTintColor = secondaryColor, onSelected = { toast2.show() }),
 *             NavBottomItem(order = 2, label = "Settings", iconResName = "ic_settings", tintColor = Color.White, selectedTintColor = secondaryColor, onSelected = {toast3.show()})
 *         )
 *
 *         Column(
 *             modifier = Modifier
 *                 .fillMaxSize()
 *                 .background(Color.White),
 *         ) {
 *             NavigationPage(
 *                 topBarTitle = "My app",
 *                 topBarTitleToCenter = false,
 *                 topBarTitleTypography = MaterialTheme.typography.titleMedium,
 *                 topBarColor = primaryColor,
 *                 topBarTitleColor = Color.White,
 *                 drawerOpenIconResName = "ic_menu",
 *                 drawerOpenIconTintColor = Color.White,
 *                 topBarEndIconResName = "ic_search",
 *                 topBarEndIconTintColor = Color.White,
 *                 drawerContainerColor = secondaryColor,
 *                 drawerTitle = "Navigation Drawer",
 *                 drawerTitleColor = Color.LightGray,
 *                 drawerTitleTypography = MaterialTheme.typography.labelLarge,
 *                 navDrawerItems = navDrawerItems,
 *                 navBottomItems = navBottomItems,
 *                 bottomBarContainerColor = primaryColor,
 *                 selectedBottomBarItemIndex = 0,
 *                 fabIconResName = "ic_add",
 *                 fabBackgroundColor = secondaryColor,
 *                 fabIconTintColor = Color.White,
 *                 onTopBarEndIconClicked = { /*TODO*/ },
 *                 onFabClicked = { /*TODO*/ })
 *             {
 *                 //screen content (compose stuff here based on what should be displayed after taking an action)
 *             }
 *         }
 *         ```
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage(
    topBarTitle: String,
    topBarTitleToCenter: Boolean,
    topBarTitleTypography: TextStyle,
    topBarColor: Color,
    topBarTitleColor: Color,
    drawerOpenIconResName: String,
    drawerOpenIconTintColor: Color,
    topBarEndIconResName: String,
    topBarEndIconTintColor: Color,
    drawerContainerColor: Color,
    drawerTitle: String,
    drawerTitleColor: Color,
    drawerTitleTypography: TextStyle,
    navDrawerItems: List<NavDrawerItem>,
    navBottomItems: List<NavBottomItem>,
    bottomBarContainerColor: Color,
    selectedBottomBarItemIndex: Int,
    fabIconResName: String,
    fabBackgroundColor: Color,
    fabIconTintColor: Color,
    onTopBarEndIconClicked: () -> Unit,
    onFabClicked: () -> Unit,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(selectedBottomBarItemIndex) }
    val drawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = drawerContainerColor
            ) {
                Text(text = drawerTitle, color = drawerTitleColor, style = drawerTitleTypography, modifier = Modifier.padding(DP_16))
                Divider()
                for (navItem in navDrawerItems) {
                    CreateNavigationDrawerItem(navItem)
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = topBarColor
                    ),
                    title = {
                        Text(
                            text = topBarTitle,
                            color = topBarTitleColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = DP_16),
                            style =  topBarTitleTypography,
                            textAlign = if (topBarTitleToCenter) TextAlign.Center else TextAlign.Justify
                        )
                    },
                    actions = {
                        IconButton(onClick = { onTopBarEndIconClicked() }) {
                            LayoutUtils.getDrawableResourceId(LocalContext.current, topBarEndIconResName)
                                ?.let { painterResource(it) }?.let {
                                    Icon(
                                        painter = it,
                                        contentDescription = ICON,
                                        tint = topBarEndIconTintColor
                                    )
                                }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            LayoutUtils.getDrawableResourceId(LocalContext.current, drawerOpenIconResName)
                                ?.let { painterResource(it) }?.let {
                                    Icon(
                                        painter = it,
                                        contentDescription = ICON,
                                        tint = drawerOpenIconTintColor
                                    )
                                }
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = bottomBarContainerColor,
                    tonalElevation = 13.dp,
                    content = {
                        navBottomItems.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItem == index,
                                onClick = {
                                    selectedItem = index
                                    item.onSelected()
                                },
                                label = { Text(text = item.label, color = if (selectedItem == index) item.selectedTintColor else item.tintColor) },
                                icon = {
                                    LayoutUtils.getDrawableResourceId(LocalContext.current, item.iconResName)
                                        ?.let { painterResource(it) }?.let {
                                            Icon(
                                                painter = it,
                                                contentDescription = ICON,
                                                tint = if (selectedItem == index) item.selectedTintColor else item.tintColor
                                            )
                                        }
                                },
                                colors = customNavigationBarItemColors(bottomBarContainerColor, item.selectedTintColor, item.tintColor)
                            )
                        }
                    },

                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onFabClicked() },
                    containerColor = fabBackgroundColor,
                    elevation = FloatingActionButtonDefaults.elevation()
                ) {
                    IconButton(onClick = { onFabClicked() }) {
                        LayoutUtils.getDrawableResourceId(LocalContext.current, fabIconResName)
                            ?.let { painterResource(it) }?.let {
                                Icon(
                                    painter = it,
                                    contentDescription = ICON,
                                    tint = fabIconTintColor
                                )
                            }
                    }
                }
            },
            content = { paddingValues ->
                screenContent(paddingValues)
            }
        )
    }
}

/**
 * A composable that represents a navigation page with a top app bar, a navigation drawer, a bottom app bar, and a floating action button.
 * You can create a list of [NavDrawerItem] objects to populate the navigation drawer and a list of [BottomAppBarAction] objects to populate the BottomAppBar,
 * with each containing the customizable properties along with their lambda functions in order for this composable to be highly customizable and reusable.
 *
 * @param topBarTitle                       The title text for the top app bar.
 * @param topBarTitleToCenter               Whether to center-align the title in the top app bar.
 * @param topBarTitleTypography             The typography for the top app bar title.
 * @param topBarColor                       The background color of the top app bar.
 * @param topBarTitleColor                  The color of the top app bar title.
 * @param drawerOpenIconResName             The resource name for the drawer open icon.
 * @param drawerOpenIconTintColor           The tint color for the drawer open icon.
 * @param topBarEndIconResName              The resource name for the top app bar's end icon.
 * @param topBarEndIconTintColor            The tint color for the top app bar's end icon.
 * @param drawerContainerColor              The background color for the navigation drawer.
 * @param drawerTitle                       The title text for the navigation drawer.
 * @param drawerTitleColor                  The color of the navigation drawer title.
 * @param drawerTitleTypography             The typography for the navigation drawer title.
 * @param navDrawerItems                    A list of navigation drawer items.
 * @param bottomBarActions                  A list of actions for the bottom app bar.
 * @param bottomAppBarContainerColor        The background color of the bottom app bar.
 * @param fabIconResName                    The resource name for the floating action button icon.
 * @param fabBackgroundColor                The background color of the floating action button.
 * @param fabIconTintColor                  The tint color for the floating action button icon.
 * @param onTopBarEndIconClicked            The lambda to be invoked when the top app bar's end icon is clicked.
 * @param onFabClicked                      The lambda to be invoked when the floating action button is clicked.
 * @param screenContent                     The content of the navigation page, displayed in the main area.
 *
 * @sample
 * ```
 *   val primaryColor = Color(0xFF6495ED)
 *   val secondaryColor = Color(0xff381E72)
 *
 *   val toast1 = Toast.makeText(this, "Toast 1 Test", Toast.LENGTH_SHORT)
 *   val toast2 = Toast.makeText(this, "Toast 2 Test", Toast.LENGTH_SHORT)
 *   val toast3 = Toast.makeText(this, "Toast 3 Test", Toast.LENGTH_SHORT)
 *   val toast4 = Toast.makeText(this, "Toast 4 Test", Toast.LENGTH_SHORT)
 *
 *    val navDrawerItems = listOf(
 *             NavDrawerItem(label = "Home", iconResName = "ic_home", onClick = {toast1.show()}),
 *             NavDrawerItem(label = "Settings", iconResName = "ic_settings", onClick = {toast2.show()}),
 *             NavDrawerItem(label = "Search", iconResName = "ic_search", onClick = {toast3.show()}),
 *             NavDrawerItem(label = "Add", iconResName = "ic_add", onClick = {toast4.show()}),
 *         )
 *
 *    val bottomAppBarActions = listOf(
 *             BottomAppBarAction(order = 0, iconResName = "ic_home", iconTintColor = Color.White, onClick = {toast1.show()}),
 *             BottomAppBarAction(order = 1, iconResName = "ic_settings", iconTintColor = Color.White, onClick = {toast2.show()}),
 *             BottomAppBarAction(order = 2, iconResName = "ic_search", iconTintColor = Color.White, onClick = {toast3.show()}),
 *         )
 *
 *         Column(
 *             modifier = Modifier
 *                 .fillMaxSize()
 *                 .background(Color.White),
 *
 *         ) {
 *         NavigationPage(
 *             topBarTitle = "My app",
 *             topBarTitleToCenter = false,
 *             topBarTitleTypography = MaterialTheme.typography.titleMedium,
 *             topBarColor = primaryColor,
 *             topBarTitleColor = Color.White,
 *             drawerOpenIconResName = "ic_menu",
 *             drawerOpenIconTintColor = Color.White,
 *             topBarEndIconResName = "ic_search",
 *             topBarEndIconTintColor = Color.White,
 *             drawerContainerColor = secondaryColor,
 *             drawerTitle = "Navigation Drawer",
 *             drawerTitleColor = Color.LightGray,
 *             bottomAppBarContainerColor = primaryColor.copy(0.9f),
 *             drawerTitleTypography = MaterialTheme.typography.labelLarge,
 *             navDrawerItems = navDrawerItems,
 *             bottomBarActions = bottomAppBarActions,
 *             fabIconResName = "ic_add",
 *             fabBackgroundColor = secondaryColor,
 *             fabIconTintColor = Color.White,
 *             onTopBarEndIconClicked = { /*TODO*/ },
 *             onFabClicked = { /*TODO*/ })
 *             {
 *                 //screen content (compose stuff here based on what should be displayed after taking an action)
 *             }
 *         }
 *         ```
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage(
    topBarTitle: String,
    topBarTitleToCenter: Boolean,
    topBarTitleTypography: TextStyle,
    topBarColor: Color,
    topBarTitleColor: Color,
    drawerOpenIconResName: String,
    drawerOpenIconTintColor: Color,
    topBarEndIconResName: String,
    topBarEndIconTintColor: Color,
    drawerContainerColor: Color,
    drawerTitle: String,
    drawerTitleColor: Color,
    drawerTitleTypography: TextStyle,
    navDrawerItems: List<NavDrawerItem>,
    bottomBarActions: List<BottomAppBarAction>,
    bottomAppBarContainerColor: Color,
    fabIconResName: String,
    fabBackgroundColor: Color,
    fabIconTintColor: Color,
    onTopBarEndIconClicked: () -> Unit,
    onFabClicked: () -> Unit,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = drawerContainerColor
            ) {
                Text(text = drawerTitle, color = drawerTitleColor, style = drawerTitleTypography, modifier = Modifier.padding(DP_16))
                Divider()
                for (navItem in navDrawerItems) {
                    CreateNavigationDrawerItem(navItem)
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = topBarColor
                    ),
                    title = {
                        Text(
                            text = topBarTitle,
                            color = topBarTitleColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = DP_16),
                            style =  topBarTitleTypography,
                            textAlign = if (topBarTitleToCenter) TextAlign.Center else TextAlign.Justify
                        )
                    },
                    actions = {
                        IconButton(onClick = { onTopBarEndIconClicked() }) {
                            LayoutUtils.getDrawableResourceId(LocalContext.current, topBarEndIconResName)
                                ?.let { painterResource(it) }?.let {
                                    Icon(
                                        painter = it,
                                        contentDescription = ICON,
                                        tint = topBarEndIconTintColor
                                    )
                                }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            LayoutUtils.getDrawableResourceId(LocalContext.current, drawerOpenIconResName)
                                ?.let { painterResource(it) }?.let {
                                    Icon(
                                        painter = it,
                                        contentDescription = ICON,
                                        tint = drawerOpenIconTintColor
                                    )
                                }
                        }
                    }
                )
            },
            bottomBar = {
                androidx.compose.material3.BottomAppBar(
                    containerColor = bottomAppBarContainerColor,
                    actions = {
                        for (action in bottomBarActions) {
                            CreateBottomAppBarAction(action)
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { onFabClicked() },
                            containerColor = fabBackgroundColor,
                            elevation = FloatingActionButtonDefaults.elevation()
                        ) {
                            IconButton(onClick = { onFabClicked() }) {
                                LayoutUtils.getDrawableResourceId(LocalContext.current, fabIconResName)
                                    ?.let { painterResource(it) }?.let {
                                        Icon(
                                            painter = it,
                                            contentDescription = ICON,
                                            tint = fabIconTintColor
                                        )
                                    }
                            }
                        }
                    }
                )
            },
            content = { paddingValues ->
                screenContent(paddingValues)
            }
        )
    }
}

/**
 * A customizable top bar Composable that can display a title and icons at both ends.
 *
 * @param title                     The title text to be displayed in the top bar.
 * @param titleToCenter             Boolean that indicates if the title should be at the center of the top bar or not.
 * @param titleTypography           Typography to be applied at the top bar title text.
 * @param topBarColor               The background color of the top bar.
 * @param titleColor                The color of the title text.
 * @param startIconResName          The name of the start icon resource.
 * @param startIconTintColor        The color of the start icon.
 * @param endIconResName            The name of the end icon resource.
 * @param endIconTintColor          The color of the end icon.
 * @param onStartIconClicked        The callback function to be invoked when the start icon is clicked.
 * @param onEndIconClicked          The callback function to be invoked when the end icon is clicked.
 * @param screenContent             A Composable lambda function that represents the content to be displayed below the top bar.
 *
 * Example usage:
 *
 * ```
 * StyledTopBar(
 *     title = "My Top Bar",
 *     titleToCenter = false,
 *     titleTypography = MaterialTheme.typography.titleMedium,
 *     topBarColor = Color.Blue,
 *     titleColor = Color.White,
 *     startIconResName = "ic_back",
 *     startIconTintColor = Color.White,
 *     endIconResName = "ic_settings",
 *     endIconTintColor = Color.White,
 *     onStartIconClicked = {
 *         // Handle start icon click
 *     },
 *     onEndIconClicked = {
 *         // Handle end icon click
 *     }
 * ) { paddingValues ->
 *     // Content to be displayed in the screen
 * }
 * ```
 */
@Composable
fun StyledTopBar(
    title: String,
    titleToCenter: Boolean,
    titleTypography: TextStyle,
    topBarColor: Color,
    titleColor: Color,
    startIconResName: String,
    startIconTintColor: Color,
    endIconResName: String,
    endIconTintColor: Color,
    onStartIconClicked: () -> Unit,
    onEndIconClicked: () -> Unit,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopBarComponent(
                title = title,
                titleTypography = titleTypography,
                titleToCenter = titleToCenter,
                topBarColor = topBarColor,
                titleColor = titleColor,
                startIconResName = startIconResName,
                startIconTintColor = startIconTintColor,
                endIconResName = endIconResName,
                endIconTintColor = endIconTintColor,
                onStartIconClicked = { onStartIconClicked() },
                onEndIconClicked = { onEndIconClicked() }
            )
        },
        content = { paddingValues ->
            screenContent(paddingValues)
        }
    )
}

/**
 * A customized Jetpack Compose [Scaffold] with a top app bar that allows you to specify a title,
 * top bar color, start and end icons, and their respective click handlers.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarComponent(
    title: String,
    titleToCenter: Boolean,
    titleTypography: TextStyle,
    topBarColor: Color,
    titleColor: Color,
    startIconResName: String,
    startIconTintColor: Color,
    endIconResName: String,
    endIconTintColor: Color,
    onStartIconClicked: () -> Unit,
    onEndIconClicked: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = topBarColor
        ),
        title = {
            Text(
                text = title,
                color = titleColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = DP_16),
                style =  titleTypography,
                textAlign = if (titleToCenter) TextAlign.Center else TextAlign.Justify
            )
        },
        actions = {
            IconButton(onClick = { onEndIconClicked() }) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, endIconResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = ICON,
                            tint = endIconTintColor
                        )
                    }
            }
        },
        navigationIcon = {
            IconButton(onClick = { onStartIconClicked() }) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, startIconResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = ICON,
                            tint = startIconTintColor
                        )
                    }
            }
        }
    )
}

/**
 * A Jetpack Compose composable that displays a top app bar with collapsible behavior and customizable styling.
 *
 * @param title               The title text displayed in the top app bar.
 * @param titleToCenter       Boolean that indicates if the title should be at the center of the top bar or not.
 * @param titleTypography     Typography to be applied at the top bar title text.
 * @param topBarColor         The background color of the top app bar.
 * @param titleColor          The color of the title text.
 * @param startIconResName    The name of the resource for the start icon.
 * @param startIconTintColor  The color of the start icon.
 * @param endIconResName      The name of the resource for the end icon.
 * @param endIconTintColor    The color of the end icon.
 * @param onStartIconClicked  A lambda function to execute when the start icon is clicked.
 * @param onEndIconClicked    A lambda function to execute when the end icon is clicked.
 * @param screenContent       The content to be displayed beneath the top app bar.
 *
 * Example usage:
 * ```
 * StyledTopBarCollapsable(
 *     title = "Collapsible Top Bar",
 *     titleToCenter = false,
 *     titleTypography = MaterialTheme.typography.titleMedium,
 *     topBarColor = Color.Blue,
 *     titleColor = Color.White,
 *     startIconResName = "ic_start_icon",
 *     startIconTintColor = Color.White,
 *     endIconResName = "ic_end_icon",
 *     endIconTintColor = Color.White,
 *     onStartIconClicked = { /* handle start icon click action */ },
 *     onEndIconClicked = { /* handle end icon click action */ }
 * ) { paddingValues ->
 *     // Content to be displayed below the top app bar
 * }
 * ```
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyledTopBarCollapsable(
    title: String,
    titleToCenter: Boolean,
    titleTypography: TextStyle,
    topBarColor: Color,
    titleColor: Color,
    startIconResName: String,
    startIconTintColor: Color,
    endIconResName: String,
    endIconTintColor: Color,
    onStartIconClicked: () -> Unit,
    onEndIconClicked: () -> Unit,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState(),
        snapAnimationSpec = spring(stiffness = Spring.StiffnessMedium)
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topBarColor,
                    titleContentColor = titleColor,
                ),
                title = {
                    Text(
                        text = title,
                        color = titleColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = DP_16),
                        style =  titleTypography,
                        textAlign = if (titleToCenter) TextAlign.Center else TextAlign.Justify
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onStartIconClicked() }) {
                        LayoutUtils.getDrawableResourceId(LocalContext.current, startIconResName)
                            ?.let { painterResource(it) }?.let {
                                Icon(
                                    painter = it,
                                    contentDescription = ICON,
                                    tint = startIconTintColor
                                )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { onEndIconClicked() }) {
                        LayoutUtils.getDrawableResourceId(LocalContext.current, endIconResName)
                            ?.let { painterResource(it) }?.let {
                                Icon(
                                    painter = it,
                                    contentDescription = ICON,
                                    tint = endIconTintColor
                                )
                        }
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { paddingValues ->
        screenContent(paddingValues)
    }
}

/**
 * A composable function that creates a styled bottom app bar with customizable actions and a floating action button (FAB).
 * The buttons (actions) of the bottom app bar can be easily created by leveraging the [BottomAppBarAction] object.
 * A [BottomAppBarAction] contains data about the order, icon and color, as well as an onClick function that will trigger an event (e.g. open another fragment) when clicked.
 * This way the data for each action is bundled up, making this composable a lot more flexible and reusable.
 *
 * @param actions                   A list of [BottomAppBarAction] objects representing the actions in the bottom app bar.
 * @param fabBackgroundColor        The background color of the floating action button (FAB).
 * @param fabIconResName            The name of the icon resource to use for the FAB icon.
 * @param fabIconTintColor          The tint color for the FAB icon.
 * @param onFabClick                Lambda function that triggers an event when the FAB is clicked.
 * @param screenContent             The composable content for the main screen content.
 *
 * @sample
 * ```
 *     StyledBottomAppBar(
 *         actions = listOf(
 *             BottomAppBarAction(
 *                 iconResName = "ic_home",
 *                 iconTintColor = Color.Blue,
 *                 onClick = {
 *                     // Handle the action's click event, e.g., navigate to a specific screen
 *                 }
 *             ),
 *             // Add more BottomAppBarAction as needed
 *         ),
 *         bottomAppBarContainerColor = Color.Gray,
 *         fabBackgroundColor = Color.Blue,
 *         fabIconResName = "ic_add",
 *         fabIconTintColor = Color.White,
 *         onFabClick = {
 *            // trigger an event when the fab is clicked
 *        }
 *     ) { paddingValues ->
 *         // Define the main screen content composable here
 *     }
 *     ```
 */
@Composable
fun StyledBottomAppBar(
    actions: List<BottomAppBarAction>,
    bottomAppBarContainerColor: Color,
    fabBackgroundColor: Color,
    fabIconResName: String,
    fabIconTintColor: Color,
    onFabClick: () -> Unit,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            androidx.compose.material3.BottomAppBar(
                containerColor = bottomAppBarContainerColor,
                actions = {
                    for (action in actions) {
                        CreateBottomAppBarAction(action)
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { onFabClick() },
                        containerColor = fabBackgroundColor,
                        elevation = FloatingActionButtonDefaults.elevation()
                    ) {
                        IconButton(onClick = { onFabClick() }) {
                            LayoutUtils.getDrawableResourceId(LocalContext.current, fabIconResName)
                                ?.let { painterResource(it) }?.let {
                                    Icon(
                                        painter = it,
                                        contentDescription = ICON,
                                        tint = fabIconTintColor
                                    )
                                }
                        }
                    }
                }
            )
        },
    ) { paddingValues ->
        screenContent(paddingValues)
    }
}

/**
 * Composable function to display a simple bottom sheet.
 *
 * @param toShowModal          A boolean indicating whether to initially show the bottom sheet.
 * @param fabBackgroundColor   The background color for the Floating Action Button (FAB) that triggers the bottom sheet.
 * @param fabIconResName       The resource name of the icon to be displayed on the FAB.
 * @param fabIconTintColor     The tint color for the FAB icon.
 * @param fabText              The text to be displayed on the FAB.
 * @param fabTextColor         The color of the text on the FAB.
 * @param fabShape             The shape of the FAB.
 * @param screenContent        A lambda for the content to be displayed on the main screen.
 * @param sheetContent         A lambda for the content to be displayed within the bottom sheet.
 *
 * This Composable function creates a simple bottom sheet that can be triggered by a FAB. The bottom sheet
 * can be hidden or shown based on the `toShowModal` parameter and allows for customization of the FAB appearance.
 *
 * Example usage:
 * ```
 * SimpleBottomSheet(
 *     toShowModal = false,
 *     fabBackgroundColor = Color.Blue,
 *     fabIconResName = "ic_add",
 *     fabIconTintColor = Color.White,
 *     fabText = "Add Item",
 *     fabTextColor = Color.White,
 *     fabShape = CircleShape,
 *     screenContent = { paddingValues ->
 *         // Content to display on the main screen
 *     },
 *     sheetContent = {
 *         // Content to display within the bottom sheet
 *     }
 * )
 * ```
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleBottomSheet(
    toShowModal: Boolean,
    fabBackgroundColor: Color,
    fabIconResName: String,
    fabIconTintColor: Color,
    fabText: String,
    fabTextColor: Color,
    fabShape: Shape,
    screenContent: @Composable (PaddingValues) -> Unit,
    sheetContent: @Composable () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(toShowModal) }

    Scaffold(
        floatingActionButton = {
            ExtendedFABWithIcon(
                backgroundColor = fabBackgroundColor,
                iconResName = fabIconResName,
                iconTintColor = fabIconTintColor,
                textValue = fabText,
                textColor = fabTextColor,
                typography = MaterialTheme.typography.labelMedium,
                fabShape = fabShape,
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { paddingValues ->
        screenContent(paddingValues)
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                sheetContent()
            }
        }

    }
}

/**
 * A composable function that creates a styled layout with a top bar, bottom app bar with actions, and a floating action button (FAB).
 * The buttons (actions) of the bottom app bar can be easily created by leveraging the [BottomAppBarAction] object.
 * A [BottomAppBarAction] contains data about the order, icon and color, as well as an onClick function that will trigger an event (e.g. open another fragment) when clicked.
 * This way the data for each action is bundled up, making this composable a lot more flexible and reusable.
 *
 * @param actions                           A list of [BottomAppBarAction] objects representing the actions in the bottom app bar.
 * @param topBarTitle                       The title text to display in the top app bar.
 * @param topBarTitleToCenter               Boolean that indicates if the title should be at the center of the top bar or not.
 * @param topBarTitleTypography             Typography to be applied at the top bar title text.
 * @param topBarColor                       The background color of the top app bar.
 * @param topBarTitleColor                  The text color for the title in the top app bar.
 * @param topBarStartIconResName            The name of the icon resource for the start icon in the top bar.
 * @param topBarStartIconTintColor          The tint color for the start icon in the top bar.
 * @param topBarEndIconResName              The name of the icon resource for the end icon in the top bar.
 * @param topBarEndIconTintColor            The tint color for the end icon in the top bar.
 * @param onTopBarStartIconClicked          A lambda function to handle the click event for the start icon in the top bar.
 * @param onTopBarEndIconClicked            A lambda function to handle the click event for the end icon in the top bar.
 * @param onFabClicked                      A lambda function to handle the click event for the FAB.
 * @param fabBackgroundColor                The background color of the floating action button (FAB).
 * @param fabIconResName                    The name of the icon resource to use for the FAB icon.
 * @param fabIconTintColor                  The tint color for the FAB icon.
 * @param screenContent                     The composable content for the main screen content.
 *
 * @sample
 * ```
 *     StyledBarLayoutWithFab(
 *         actions = listOf(
 *             BottomAppBarAction(
 *                 iconResName = "ic_home",
 *                 iconTintColor = Color.Blue,
 *                 onClick = {
 *                     // Handle the action's click event, e.g., navigate to a specific screen
 *                 }
 *             ),
 *             // Add more BottomAppBarAction as needed
 *         ),
 *         topBarTitle = "Your App",
 *         topBarTitleToCenter = false,
 *         topBarTitleTypography = MaterialTheme.typography.titleMedium,
 *         topBarColor = Color.Blue,
 *         topBarTitleColor = Color.White,
 *         topBarStartIconResName = "ic_menu",
 *         topBarStartIconTintColor = Color.White,
 *         topBarEndIconResName = "ic_search",
 *         topBarEndIconTintColor = Color.White,
 *         onTopBarStartIconClicked = {
 *             // Handle the start icon click event
 *         },
 *         onTopBarEndIconClicked = {
 *             // Handle the end icon click event
 *         },
 *         onFabClicked = {
 *             // Handle the FAB click event
 *         },
 *         fabBackgroundColor = Color.Blue,
 *         fabIconResName = "ic_add",
 *         fabIconTintColor = Color.White
 *     ) { paddingValues ->
 *         // Define the main screen content composable here
 *     }
 *     ```
 */
@Composable
fun StyledBarLayoutWithFab(
    actions: List<BottomAppBarAction>,
    topBarTitle: String,
    topBarTitleToCenter: Boolean,
    topBarTitleTypography: TextStyle,
    topBarColor: Color,
    topBarTitleColor: Color,
    topBarStartIconResName: String,
    topBarStartIconTintColor: Color,
    topBarEndIconResName: String,
    topBarEndIconTintColor: Color,
    onTopBarStartIconClicked: () -> Unit,
    onTopBarEndIconClicked: () -> Unit,
    onFabClicked: () -> Unit,
    fabBackgroundColor: Color,
    fabIconResName: String,
    fabIconTintColor: Color,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopBarComponent(
                title = topBarTitle,
                titleTypography = topBarTitleTypography,
                titleToCenter = topBarTitleToCenter,
                topBarColor = topBarColor,
                titleColor = topBarTitleColor,
                startIconResName = topBarStartIconResName,
                startIconTintColor = topBarStartIconTintColor,
                endIconResName = topBarEndIconResName,
                endIconTintColor = topBarEndIconTintColor,
                onStartIconClicked = { onTopBarStartIconClicked() },
                onEndIconClicked = { onTopBarEndIconClicked() }
            )
        },
        bottomBar = {
            androidx.compose.material3.BottomAppBar(
                actions = {
                    for (action in actions) {
                        CreateBottomAppBarAction(action)
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { onFabClicked() },
                        containerColor = fabBackgroundColor,
                        elevation = FloatingActionButtonDefaults.elevation()
                    ) {
                        IconButton(onClick = { onFabClicked() }) {
                            LayoutUtils.getDrawableResourceId(LocalContext.current, fabIconResName)
                                ?.let { painterResource(it) }?.let {
                                    Icon(
                                        painter = it,
                                        contentDescription = ICON,
                                        tint = fabIconTintColor
                                    )
                                }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        screenContent(paddingValues)
    }
}

/**
 * A composable function that creates a customizable top bar with a title (optional) and a navigation icon that opens a styled navigation drawer with menu items.
 * The navigation items/entries for the drawer can be easily created by leveraging the [NavDrawerItem] object.
 * A [NavDrawerItem] contains data about the label, icon and colors, as well as an onClick function that will trigger an event (e.g. open another fragment) when clicked.
 * This way the data for each item is bundled up, making this composable a lot more flexible and reusable.
 *
 * @param navDrawerItems                A list of [NavDrawerItem] objects representing the items in the navigation drawer.
 * @param topBarTitle                   The title text to display in the top app bar (optional).
 * @param topBarColor                   The background color of the top app bar.
 * @param topBarTitleColor              The text color for the title in the top app bar (optional).
 * @param drawerOpenIconResName         The name of the icon resource to use for opening the drawer.
 * @param drawerOpenIconTintColor       The tint color for the drawer open icon.
 * @param drawerTitle                   The title text to display at the top of the navigation drawer.
 * @param drawerTitleTypography         The typography style for the drawer title.
 * @param drawerTitleColor              The text color for the drawer title.
 * @param drawerContainerColor          The background color of the navigation drawer.
 * @param screenContent                 The composable content for the main screen content.
 *
 * @sample
 * ```
 *     NavigationDrawerFromTopBar(
 *         navDrawerItems = listOf(
 *             NavDrawerItem(
 *                 label = "Home",
 *                 iconResName = "ic_home",
 *                 onClick = {
 *                     // Handle navigation to the home screen
 *                 }
 *             ),
 *             // Add more NavItems as needed
 *         ),
 *         topBarTitle = "Your App",
 *         topBarColor = Color.Blue,
 *         topBarTitleColor = Color.White,
 *         drawerOpenIconResName = "ic_menu",
 *         drawerOpenIconTintColor = Color.White,
 *         drawerTitle = "Menu",
 *         drawerTitleTypography = MaterialTheme.typography.titleMedium,
 *         drawerTitleColor = Color.Black,
 *         drawerContainerColor = Color.White,
 *     ) { paddingValues ->
 *         // Define the main screen content composable here
 *     }
 *     ```
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerFromTopBar(
    navDrawerItems: List<NavDrawerItem>,
    topBarTitle: String = EMPTY,
    topBarColor: Color,
    topBarTitleColor: Color = Color.Black,
    drawerOpenIconResName: String,
    drawerOpenIconTintColor: Color,
    drawerTitle: String,
    drawerTitleTypography: TextStyle,
    drawerTitleColor: Color,
    drawerContainerColor: Color,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = drawerContainerColor
            ) {
                Text(text = drawerTitle, color = drawerTitleColor, style = drawerTitleTypography, modifier = Modifier.padding(DP_16))
                Divider()
                for (navItem in navDrawerItems) {
                    CreateNavigationDrawerItem(navItem)
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = topBarColor
                    ),
                    title = { Text(text = topBarTitle, color = topBarTitleColor) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            LayoutUtils.getDrawableResourceId(LocalContext.current, drawerOpenIconResName)
                                ?.let { painterResource(it) }?.let {
                                    Icon(
                                        painter = it,
                                        contentDescription = ICON,
                                        tint = drawerOpenIconTintColor
                                    )
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            screenContent(paddingValues)
        }
    }
}

/**
 * A composable function that creates a bottom navigation bar with a set of navigation bar items.
 * The navigation bar items/entries for the drawer can be easily created by leveraging the [NavBottomItem] object.
 * A [NavBottomItem] contains data about the label, icon and colors, as well as an onSelected function that will trigger an event (e.g. open another fragment) when selected.
 * This way the data for each item is bundled up, making this composable a lot more flexible and reusable.
 *
 * @param items                     A list of [NavBottomItem] objects representing the navigation items in the bar.
 * @param containerColor            The background color of the navigation bar container.
 * @param selectedItemIndex         The index of the initially selected item in the navigation bar.
 * @param screenContent             The composable content for the main screen content.
 *
 *
 * @sample
 * ```
 *     val navItems = listOf(
 *         NavBottomItem("Home", "ic_home", "ic_home_selected", Color.Gray, Color.Blue, { /* Navigate to home */ }),
 *         NavBottomItem("Search", "ic_search", "ic_search_selected", Color.Gray, Color.Blue, { /* Navigate to search */ }),
 *         NavBottomItem("Profile", "ic_profile", "ic_profile_selected", Color.Gray, Color.Blue, { /* Navigate to profile */ })
 *     )
 *     BottomNavigationBar(
 *         items = navItems,
 *         containerColor = Color.White,
 *         selectedItemIndex = 0
 *     )
 *     { paddingValues ->
 *          // Define the main screen content composable here
 *     }
 *     ```
 */
@Composable
fun BottomNavigationBar(
    items: List<NavBottomItem>,
    containerColor: Color,
    selectedItemIndex: Int,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(selectedItemIndex) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = containerColor,
                tonalElevation = 13.dp,
                content = {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                item.onSelected()
                            },
                            label = { Text(text = item.label, color = if (selectedItem == index) item.selectedTintColor else item.tintColor) },
                            icon = {
                                LayoutUtils.getDrawableResourceId(LocalContext.current, item.iconResName)
                                    ?.let { painterResource(it) }?.let {
                                        Icon(
                                            painter = it,
                                            contentDescription = ICON,
                                            tint = if (selectedItem == index) item.selectedTintColor else item.tintColor
                                        )
                                    }
                            },
                            colors = customNavigationBarItemColors(containerColor, item.selectedTintColor, item.tintColor)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        screenContent(paddingValues)
    }
}

/**
 * A composable function to create a navigation drawer item.
 *
 * @param item The [NavDrawerItem] containing information about the item, including icon, label, and colors.
 *
 */
@Composable
private fun CreateNavigationDrawerItem(item: NavDrawerItem) {
    NavigationDrawerItem(
        icon = {
            LayoutUtils.getDrawableResourceId(LocalContext.current, item.iconResName)
                ?.let { painterResource(it) }?.let {
                    Icon(
                        painter = it,
                        contentDescription = ICON,
                        tint = item.iconTintColor
                    )
            }
        },
        colors = customNavItemSelectedColors(
            selectedContainerColor = item.selectedContainerColor,
            selectedIconColor = item.selectedIconColor,
            selectedTextColor = item.selectedTextColor
        ),
        label = { Text(text = item.label, color = item.labelColor) },
        selected = true,
        onClick = { item.onClick() }
    )
}

/**
 * A composable function to create a bottom app bar action, typically used for navigation or menu items.
 *
 * @param action The [BottomAppBarAction] containing information about the action, including icon, colors, and click behavior.
 */
@Composable
private fun CreateBottomAppBarAction(action: BottomAppBarAction) {
    IconButton(onClick = { action.onClick() }) {
        LayoutUtils.getDrawableResourceId(LocalContext.current, action.iconResName)
            ?.let { painterResource(it) }?.let {
                Icon(
                    painter = it,
                    contentDescription = ICON,
                    tint = action.iconTintColor
                )
            }
    }
}

/**
 * A styled [TabRow] with a custom indicator animation that provides a tabbed navigation interface.
 *
 * @param items                         List of [TabItem] representing individual tabs in the row.
 * @param selectedTabIndex              The index of the initially selected tab.
 * @param tabRowContainerColor          The background color of the tab row.
 * @param selectedTabColor              The color for the selected tab.
 * @param unselectedTabColor            The color for unselected tabs.
 * @param labelTypography               The text style for the tab labels.
 *
 * @sample
 * val tabItems = listOf(
 *             TabItem(0, "Tab One") { toast1.show() },
 *             TabItem(1, "Tab Two") { toast1.show() },
 *             TabItem(2, "Tab Three") { toast1.show() },
 *             TabItem(3, "Tab Four") { toast1.show() },
 *         )
 *
 *         StyledTabRowWithIndicator(
 *                 items = tabItems,
 *                 selectedTabIndex = 0,
 *                 tabRowContainerColor = Color(0xffD0BCFF),
 *                 selectedTabColor = Color(0xff381E72),
 *                 unselectedTabColor = Color.White,
 *                 labelTypography = MaterialTheme.typography.labelMedium
 *             )
 */
@Composable
fun StyledTabRowWithIndicator(
    items: List<TabItem>,
    selectedTabIndex: Int,
    tabRowContainerColor: Color,
    selectedTabColor: Color,
    unselectedTabColor: Color,
    labelTypography: TextStyle,
) {
    var selectedTab by remember { mutableIntStateOf(selectedTabIndex) }

    TabRow(
        selectedTabIndex = selectedTab,
        containerColor = tabRowContainerColor,
        indicator = { tabPositions ->
            AnimatedTabRowIndicator(
                tabPositions = tabPositions,
                selectedTabIndex = selectedTab,
                indicatorColor = selectedTabColor
            )
        },
        tabs = {
            items.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier.background(tabRowContainerColor),
                    selected = selectedTab == index,
                    onClick = {
                        selectedTab = index
                        item.onClick()
                    },
                ) {
                    Column(
                        Modifier
                            .padding(TEN.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = item.label,
                            style = labelTypography,
                            color = if (selectedTab == index) selectedTabColor else unselectedTabColor,
                        )
                    }
                }
            }
        },
    )
}

/**
 * A custom animated tab indicator that visually represents the currently selected tab in a TabRow.
 *
 * @param tabPositions          The list of tab positions provided by the TabRow.
 * @param selectedTabIndex      The index of the selected tab.
 * @param indicatorColor        The color of the indicator.
 */
@Composable
private fun AnimatedTabRowIndicator(
    tabPositions: List<androidx.compose.material3.TabPosition>,
    selectedTabIndex: Int,
    indicatorColor: Color
) {

    val transition = updateTransition(selectedTabIndex, label = EMPTY)

    val indicatorStart by transition.animateDp(
        transitionSpec = {
            // Handle directionality here, if we are moving to the right, we
            // want the right side of the indicator to move faster, if we are
            // moving to the left, we want the left side to move faster.
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }, label = EMPTY
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            // Handle directionality here, if we are moving to the right, we
            // want the right side of the indicator to move faster, if we are
            // moving to the left, we want the left side to move faster.
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = EMPTY
    ) {
        tabPositions[it].right
    }

    CustomTabRowIndicator(
        modifier = Modifier
            // Fill up the entire TabRow, and place the indicator at the start
            .wrapContentSize(align = Alignment.BottomStart)
            // Apply an offset from the start to correctly position the indicator around the tab
            .offset(x = indicatorStart)
            // Make the width of the indicator follow the animated width as we move between tabs
            .width(indicatorEnd - indicatorStart),
        indicatorColor = indicatorColor
    )
}

/**
 * A custom tab indicator that visually represents the currently selected tab in a TabRow.
 *
 * @param modifier              The modifier for configuring the indicator's appearance and position.
 * @param indicatorColor        The color of the indicator.
 */
@Composable
private fun CustomTabRowIndicator(
    modifier: Modifier,
    indicatorColor: Color
) {
    Box(
        modifier
            .fillMaxSize(0.1f)
            .background(indicatorColor)
            .border(BorderStroke(1.dp, indicatorColor), RoundedCornerShape(160.dp))
    )
}

/**
 * A composable function that creates custom colors for a selected NavigationDrawerItem.
 *
 * @return A [NavigationDrawerItemDefaults] object with the specified custom color settings.
 */
@Composable
private fun customNavItemSelectedColors(
    selectedContainerColor: Color,
    selectedIconColor: Color,
    selectedTextColor: Color
): NavigationDrawerItemColors {
    return NavigationDrawerItemDefaults.colors(
        selectedContainerColor = selectedContainerColor,
        selectedIconColor = selectedIconColor,
        selectedTextColor = selectedTextColor
    )
}

/**
 * A composable function that creates custom colors for a selected NavigationBarItem.
 *
 * @return A [NavigationBarItemDefaults] object with the specified custom color settings.
 */
@Composable
private fun customNavigationBarItemColors(
    containerColor: Color,
    selectedColor: Color,
    unselectedColor: Color
): NavigationBarItemColors {
    return NavigationBarItemDefaults.colors(
        selectedIconColor = selectedColor,
        selectedTextColor = selectedColor,
        unselectedIconColor = unselectedColor,
        unselectedTextColor = unselectedColor,
        indicatorColor = containerColor
    )
}

