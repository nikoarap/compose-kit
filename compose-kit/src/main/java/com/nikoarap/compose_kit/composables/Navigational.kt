package com.nikoarap.compose_kit.composables

import androidx.compose.animation.animateColor
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.models.BottomAppBarAction
import com.nikoarap.compose_kit.models.NavBottomItem
import com.nikoarap.compose_kit.models.NavDrawerItem
import com.nikoarap.compose_kit.models.TabItem
import com.nikoarap.compose_kit.utils.Constants.Companion.DP_16
import com.nikoarap.compose_kit.utils.Constants.Companion.EMPTY
import com.nikoarap.compose_kit.utils.Constants.Companion.FIFTY
import com.nikoarap.compose_kit.utils.Constants.Companion.ICON
import com.nikoarap.compose_kit.utils.Constants.Companion.TEN
import com.nikoarap.compose_kit.utils.LayoutUtils
import kotlinx.coroutines.launch

/**
 * A customized Jetpack Compose [Scaffold] with a top app bar that allows you to specify a title,
 * top bar color, start and end icons, and their respective click handlers.
 *
 * @param title                     The title text to display in the top app bar.
 * @param topBarColor               The background color of the top app bar.
 * @param titleColor                The text color of the title in the top app bar.
 * @param startIconResName          The resource name for the icon on the left (start) side of the top app bar.
 * @param startIconTintColor        The tint color for the start icon.
 * @param endIconResName            The resource name for the icon on the right (end) side of the top app bar.
 * @param endIconTintColor          The tint color for the end icon.
 * @param onStartIconClicked        The click handler for the start icon.
 * @param onEndIconClicked          The click handler for the end icon.
 * @param screenContent             The composable function for defining the main content of the screen, which receives [PaddingValues] for controlling content padding.
 *
 * Example usage:
 * ```kotlin
 * CustomizedTopBarScaffold(
 *     title = "My Custom Scaffold",
 *     topBarColor = Color.Blue,
 *     titleColor = Color.White,
 *     startIconResName = "ic_menu",
 *     startIconTintColor = Color.White,
 *     endIconResName = "ic_search",
 *     endIconTintColor = Color.White,
 *     onStartIconClicked = { /* Handle start icon click */ },
 *     onEndIconClicked = { /* Handle end icon click */ }
 * ) { paddingValues ->
 *     // Main screen content goes here
 *     // You can use 'paddingValues' to control content padding
 *     Column(
 *         modifier = Modifier.padding(paddingValues),
 *         verticalArrangement = Arrangement.Center,
 *         horizontalAlignment = Alignment.CenterHorizontally
 *     ) {
 *         Text("Welcome to My Custom Scaffold", color = Color.Black)
 *         // Add your content here
 *     }
 * }
 * ```
 */
@Composable
fun StyledTopBar(
    title: String,
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
@Composable
private fun TopBarComponent(
    title: String,
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
        backgroundColor = topBarColor,
        title = { Text(text = title, color = titleColor) },
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
 * ```kotlin
 * StyledTopBarCollapsable(
 *     title = "Collapsible Top Bar",
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
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

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
                        title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
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
 * @param screenContent             The composable content for the main screen content.
 *
 * @sample
 *     StyledBottomAppBar(
 *         actions = listOf(
 *             BottomAppBarAction(
 *                 iconResName = "ic_home",
 *                 iconTintColor = Color.Blue,
 *                 selectedIconTintColor = Color.Red,
 *                 onClick = {
 *                     // Handle the action's click event, e.g., navigate to a specific screen
 *                 }
 *             ),
 *             // Add more BottomAppBarAction as needed
 *         ),
 *         fabBackgroundColor = Color.Blue,
 *         fabIconResName = "ic_add",
 *         fabIconTintColor = Color.White
 *     ) { paddingValues ->
 *         // Define the main screen content composable here
 *     }
 */
@Composable
fun StyledBottomAppBar(
    actions: List<BottomAppBarAction>,
    fabBackgroundColor: Color,
    fabIconResName: String,
    fabIconTintColor: Color,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            androidx.compose.material3.BottomAppBar(
                actions = {
                    for (action in actions) {
                        CreateBottomAppBarAction(action)
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        backgroundColor = fabBackgroundColor,
                        elevation = FloatingActionButtonDefaults.elevation()
                    ) {
                        IconButton(onClick = { /* do something */ }) {
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
 * ```kotlin
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
    val scaffoldState = rememberScaffoldState()
    var showBottomSheet by remember { mutableStateOf(toShowModal) }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            ExtendedFABWithIcon(
                backgroundColor = fabBackgroundColor,
                iconResName = fabIconResName,
                iconTintColor = fabIconTintColor,
                textValue = fabText,
                textColor = fabTextColor,
                typography = MaterialTheme.typography.button,
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
 * @param topBarColor                       The background color of the top app bar.
 * @param topBarTitleColor                  The text color for the title in the top app bar.
 * @param topBarStartIconResName            The name of the icon resource for the start icon in the top bar.
 * @param topBarStartIconTintColor          The tint color for the start icon in the top bar.
 * @param topBarEndIconResName              The name of the icon resource for the end icon in the top bar.
 * @param topBarEndIconTintColor            The tint color for the end icon in the top bar.
 * @param onTopBarStartIconClicked          A lambda function to handle the click event for the start icon in the top bar.
 * @param onTopBarEndIconClicked            A lambda function to handle the click event for the end icon in the top bar.
 * @param fabBackgroundColor                The background color of the floating action button (FAB).
 * @param fabIconResName                    The name of the icon resource to use for the FAB icon.
 * @param fabIconTintColor                  The tint color for the FAB icon.
 * @param screenContent                     The composable content for the main screen content.
 *
 * @sample
 *     StyledBarLayoutWithFab(
 *         actions = listOf(
 *             BottomAppBarAction(
 *                 iconResName = "ic_home",
 *                 iconTintColor = Color.Blue,
 *                 selectedIconTintColor = Color.Red,
 *                 onClick = {
 *                     // Handle the action's click event, e.g., navigate to a specific screen
 *                 }
 *             ),
 *             // Add more BottomAppBarAction as needed
 *         ),
 *         topBarTitle = "Your App",
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
 *         fabBackgroundColor = Color.Blue,
 *         fabIconResName = "ic_add",
 *         fabIconTintColor = Color.White
 *     ) { paddingValues ->
 *         // Define the main screen content composable here
 *     }
 */
@Composable
fun StyledBarLayoutWithFab(
    actions: List<BottomAppBarAction>,
    topBarTitle: String,
    topBarColor: Color,
    topBarTitleColor: Color,
    topBarStartIconResName: String,
    topBarStartIconTintColor: Color,
    topBarEndIconResName: String,
    topBarEndIconTintColor: Color,
    onTopBarStartIconClicked: () -> Unit,
    onTopBarEndIconClicked: () -> Unit,
    fabBackgroundColor: Color,
    fabIconResName: String,
    fabIconTintColor: Color,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopBarComponent(
                title = topBarTitle,
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
                        onClick = { /* do something */ },
                        backgroundColor = fabBackgroundColor,
                        elevation = FloatingActionButtonDefaults.elevation()
                    ) {
                        IconButton(onClick = { /* do something */ }) {
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
 *     StyledNavigationDrawer(
 *         navItems = listOf(
 *             NavItem(
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
 *         drawerTitleTypography = TextStyle(fontWeight = FontWeight.Bold),
 *         drawerTitleColor = Color.Black,
 *         drawerContainerColor = Color.White,
 *     ) { paddingValues ->
 *         // Define the main screen content composable here
 *     }
 */
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
                Text(text = drawerTitle, color = drawerTitleColor, style = drawerTitleTypography, modifier = Modifier.padding(DP_16.dp))
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
                    backgroundColor = topBarColor,
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
 * @param barContainerColor         The background color of the navigation bar container.
 * @param barContentColor           The color of the navigation bar content, including icons and labels.
 * @param selectedItemIndex         The index of the initially selected item in the navigation bar.
 *
 * @sample
 *     val navItems = listOf(
 *         NavBottomItem("Home", "ic_home", "ic_home_selected", Color.Gray, Color.Blue, { /* Navigate to home */ }),
 *         NavBottomItem("Search", "ic_search", "ic_search_selected", Color.Gray, Color.Blue, { /* Navigate to search */ }),
 *         NavBottomItem("Profile", "ic_profile", "ic_profile_selected", Color.Gray, Color.Blue, { /* Navigate to profile */ })
 *     )
 *     BottomNavigationBar(
 *         items = navItems,
 *         barContainerColor = Color.White,
 *         barContentColor = Color.Black,
 *         selectedItemIndex = 0
 *     )
 */
@Composable
fun BottomNavigationBar(
    items: List<NavBottomItem>,
    barContainerColor: Color,
    barContentColor: Color,
    selectedItemIndex: Int,
) {
    var selectedItem by remember { mutableIntStateOf(selectedItemIndex) }

    NavigationBar(
        containerColor = barContainerColor,
        contentColor = barContentColor,
        content = {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        item.onSelected
                    },
                    label = { Text(text = item.label) },
                    icon = {
                        LayoutUtils.getDrawableResourceId(LocalContext.current, item.iconResName)
                            ?.let { painterResource(it) }?.let {
                                Icon(
                                    painter = it,
                                    contentDescription = ICON,
                                    tint = barContentColor
                                )
                            }
                    },
                    colors = customNavigationBarItemColors(item.selectedTintColor, item.tintColor)
                )
            }
        }
    )
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
        label = { Text(text = item.label) },
        selected = false,
        onClick = { item.onClick }
    )
}

/**
 * A composable function to create a bottom app bar action, typically used for navigation or menu items.
 *
 * @param action The [BottomAppBarAction] containing information about the action, including icon, colors, and click behavior.
 */
@Composable
private fun CreateBottomAppBarAction(action: BottomAppBarAction) {
    IconButton(onClick = { action.onClick }) {
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

@Composable
fun StyledTabRowWithIndicator(
    items: List<TabItem>,
    selectedTabIndex: Int,
    tabRowContainerColor: Color,
    tabRowContentColor: Color,
    primaryIndicatorColor: Color,
    secondaryIndicatorColor: Color,
    tertiaryIndicatorColor: Color
) {
    val state by remember { mutableStateOf(selectedTabIndex) }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = tabRowContainerColor,
        contentColor = tabRowContentColor,
        indicator = { tabPositions ->
            AnimatedTabRowIndicator(
                tabPositions = tabPositions,
                selectedTabIndex = selectedTabIndex,
                primaryIndicatorColor = primaryIndicatorColor,
                secondaryIndicatorColor = secondaryIndicatorColor,
                tertiaryIndicatorColor = tertiaryIndicatorColor,
            )
        },
        tabs = {
            items.forEachIndexed { index, item ->
                CustomTab(item, index == state)
            }
        },
    )
}

@Composable
private fun AnimatedTabRowIndicator(
    tabPositions: List<androidx.compose.material3.TabPosition>,
    selectedTabIndex: Int,
    primaryIndicatorColor: Color,
    secondaryIndicatorColor: Color,
    tertiaryIndicatorColor: Color
) {
    val colors = listOf(
        primaryIndicatorColor,
        secondaryIndicatorColor,
        tertiaryIndicatorColor,
    )

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

    val indicatorColor by transition.animateColor(label = EMPTY) {
        colors[it % colors.size]
    }

    CustomTabRowIndicator(
        modifier = Modifier
            // Fill up the entire TabRow, and place the indicator at the start
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            // Apply an offset from the start to correctly position the indicator around the tab
            .offset(x = indicatorStart)
            // Make the width of the indicator follow the animated width as we move between tabs
            .width(indicatorEnd - indicatorStart),
        indicatorColor = indicatorColor
    )


}

@Composable
private fun CustomTabRowIndicator(
    modifier: Modifier,
    indicatorColor: Color
) {
    Box(
        modifier
            .padding(5.dp)
            .fillMaxSize()
            .border(BorderStroke(2.dp, indicatorColor), RoundedCornerShape(5.dp))
    )
}


@Composable
private fun CustomTab(
    item: TabItem,
    selected: Boolean
) {
    Tab(
        selected = selected,
        onClick = { item.onClick }
    ) {
        Column(
            Modifier
                .padding(TEN.dp)
                .height(FIFTY.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .size(TEN.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(color = if (selected) item.selectedColor else item.unselectedColor)
            )
            Text(
                text = item.label,
                style = item.labelTypography,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
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
    selectedColor: Color,
    unselectedColor: Color
): NavigationBarItemColors {
    return NavigationBarItemDefaults.colors(
        selectedIconColor = selectedColor,
        selectedTextColor = selectedColor,
        unselectedIconColor = unselectedColor,
        unselectedTextColor = unselectedColor,
    )
}

