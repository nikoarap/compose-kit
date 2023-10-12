package com.nikoarap.compose_kit.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nikoarap.compose_kit.utils.Constants
import com.nikoarap.compose_kit.utils.LayoutUtils

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
fun TopBarComponent(
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
                            contentDescription = Constants.IMAGE,
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
                            contentDescription = Constants.IMAGE,
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
                                    contentDescription = Constants.IMAGE,
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
                                    contentDescription = Constants.IMAGE,
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
 * A Jetpack Compose composable that displays a styled Bottom App Bar with customizable icons and a FloatingActionButton.
 *
 * @param fabBackgroundColor        The background color of the FloatingActionButton.
 * @param iconFirstResName          The name of the resource for the first icon.
 * @param iconSecondResName         The name of the resource for the second icon.
 * @param fabIconResName            The name of the resource for the FloatingActionButton icon.
 * @param iconTintColor             The color of the Bottom App Bar icons.
 * @param fabIconTintColor          The color of the FloatingActionButton icon.
 * @param onIconFirstClicked        A lambda function to execute when the first icon is clicked.
 * @param onIconSecondClicked       A lambda function to execute when the second icon is clicked.
 * @param screenContent             The content to be displayed above the Bottom App Bar.
 *
 * Example usage:
 * ```kotlin
 * StyledBottomAppBar(
 *     fabBackgroundColor = Color.Green,
 *     iconFirstResName = "ic_first_icon",
 *     iconSecondResName = "ic_second_icon",
 *     iconThirdResName = "ic_third_icon",
 *     iconFourthResName = "ic_fourth_icon",
 *     fabIconResName = "ic_fab_icon",
 *     iconTintColor = Color.White,
 *     fabIconTintColor = Color.White,
 *     onIconFirstClicked = { /* handle icon click action */ },
 *     onIconSecondClicked = { /* handle icon click action */ },
 * ) { paddingValues ->
 *     // Content to be displayed above the Bottom App Bar
 * }
 * ```
 */
@Composable
fun StyledBottomAppBar(
    fabBackgroundColor: Color,
    iconFirstResName: String,
    iconSecondResName: String,
    fabIconResName: String,
    iconTintColor: Color,
    fabIconTintColor: Color,
    onIconFirstClicked: () -> Unit,
    onIconSecondClicked: () -> Unit,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomAppBarTwoIconsAndFab(
                fabBackgroundColor = fabBackgroundColor,
                iconFirstResName = iconFirstResName,
                iconSecondResName = iconSecondResName,
                fabIconResName = fabIconResName,
                iconTintColor = iconTintColor,
                fabIconTintColor = fabIconTintColor,
                onIconFirstClicked = { onIconFirstClicked() },
                onIconSecondClicked = { onIconSecondClicked() }
            )
        },
    ) { paddingValues ->
        screenContent(paddingValues)
    }
}

/**
 * A Jetpack Compose composable that displays a styled Bottom App Bar with customizable icons and a FloatingActionButton.
 *
 * @param fabBackgroundColor        The background color of the FloatingActionButton.
 * @param iconFirstResName          The name of the resource for the first icon.
 * @param iconSecondResName         The name of the resource for the second icon.
 * @param iconThirdResName          The name of the resource for the third icon.
 * @param fabIconResName            The name of the resource for the FloatingActionButton icon.
 * @param iconTintColor             The color of the Bottom App Bar icons.
 * @param fabIconTintColor          The color of the FloatingActionButton icon.
 * @param onIconFirstClicked        A lambda function to execute when the first icon is clicked.
 * @param onIconSecondClicked       A lambda function to execute when the second icon is clicked.
 * @param onIconThirdClicked        A lambda function to execute when the third icon is clicked.
 * @param screenContent             The content to be displayed above the Bottom App Bar.
 *
 * Example usage:
 * ```kotlin
 * StyledBottomAppBar(
 *     fabBackgroundColor = Color.Green,
 *     iconFirstResName = "ic_first_icon",
 *     iconSecondResName = "ic_second_icon",
 *     iconThirdResName = "ic_third_icon",
 *     iconFourthResName = "ic_fourth_icon",
 *     fabIconResName = "ic_fab_icon",
 *     iconTintColor = Color.White,
 *     fabIconTintColor = Color.White,
 *     onIconFirstClicked = { /* handle icon click action */ },
 *     onIconSecondClicked = { /* handle icon click action */ },
 *     onIconThirdClicked = { /* handle icon click action */ },
 * ) { paddingValues ->
 *     // Content to be displayed above the Bottom App Bar
 * }
 * ```
 */
@Composable
fun StyledBottomAppBar(
    fabBackgroundColor: Color,
    iconFirstResName: String,
    iconSecondResName: String,
    iconThirdResName: String,
    fabIconResName: String,
    iconTintColor: Color,
    fabIconTintColor: Color,
    onIconFirstClicked: () -> Unit,
    onIconSecondClicked: () -> Unit,
    onIconThirdClicked: () -> Unit,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomAppBarThreeIconsAndFab(
                fabBackgroundColor = fabBackgroundColor,
                iconFirstResName = iconFirstResName,
                iconSecondResName = iconSecondResName,
                iconThirdResName = iconThirdResName,
                fabIconResName = fabIconResName,
                iconTintColor = iconTintColor,
                fabIconTintColor = fabIconTintColor,
                onIconFirstClicked = { onIconFirstClicked() },
                onIconSecondClicked = { onIconSecondClicked() },
                onIconThirdClicked = { onIconThirdClicked() }
            )
        },
    ) { paddingValues ->
        screenContent(paddingValues)
    }
}

/**
 * A Jetpack Compose composable that displays a styled Bottom App Bar with customizable icons and a FloatingActionButton.
 *
 * @param fabBackgroundColor        The background color of the FloatingActionButton.
 * @param iconFirstResName          The name of the resource for the first icon.
 * @param iconSecondResName         The name of the resource for the second icon.
 * @param iconThirdResName          The name of the resource for the third icon.
 * @param iconFourthResName         The name of the resource for the fourth icon.
 * @param fabIconResName            The name of the resource for the FloatingActionButton icon.
 * @param iconTintColor             The color of the Bottom App Bar icons.
 * @param fabIconTintColor          The color of the FloatingActionButton icon.
 * @param onIconFirstClicked        A lambda function to execute when the first icon is clicked.
 * @param onIconSecondClicked       A lambda function to execute when the second icon is clicked.
 * @param onIconThirdClicked        A lambda function to execute when the third icon is clicked.
 * @param onIconFourthClicked       A lambda function to execute when the fourth icon is clicked.
 * @param screenContent             The content to be displayed above the Bottom App Bar.
 *
 * Example usage:
 * ```kotlin
 * StyledBottomAppBar(
 *     fabBackgroundColor = Color.Green,
 *     iconFirstResName = "ic_first_icon",
 *     iconSecondResName = "ic_second_icon",
 *     iconThirdResName = "ic_third_icon",
 *     iconFourthResName = "ic_fourth_icon",
 *     fabIconResName = "ic_fab_icon",
 *     iconTintColor = Color.White,
 *     fabIconTintColor = Color.White,
 *     onIconFirstClicked = { /* handle icon click action */ },
 *     onIconSecondClicked = { /* handle icon click action */ },
 *     onIconThirdClicked = { /* handle icon click action */ },
 *     onIconFourthClicked = { /* handle icon click action */ }
 * ) { paddingValues ->
 *     // Content to be displayed above the Bottom App Bar
 * }
 * ```
 */
@Composable
fun StyledBottomAppBar(
    fabBackgroundColor: Color,
    iconFirstResName: String,
    iconSecondResName: String,
    iconThirdResName: String,
    iconFourthResName: String,
    fabIconResName: String,
    iconTintColor: Color,
    fabIconTintColor: Color,
    onIconFirstClicked: () -> Unit,
    onIconSecondClicked: () -> Unit,
    onIconThirdClicked: () -> Unit,
    onIconFourthClicked: () -> Unit,
    screenContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomAppBarFourIconsAndFab(
                fabBackgroundColor = fabBackgroundColor,
                iconFirstResName = iconFirstResName,
                iconSecondResName = iconSecondResName,
                iconThirdResName = iconThirdResName,
                iconFourthResName = iconFourthResName,
                fabIconResName = fabIconResName,
                iconTintColor = iconTintColor,
                fabIconTintColor = fabIconTintColor,
                onIconFirstClicked = { onIconFirstClicked() },
                onIconSecondClicked = { onIconSecondClicked() },
                onIconThirdClicked = { onIconThirdClicked() },
                onIconFourthClicked = {onIconFourthClicked () }
            )
        },
    ) { paddingValues ->
        screenContent(paddingValues)
    }
}

/**
 * A Jetpack Compose composable that displays a styled Bottom App Bar with two customizable icons and a FloatingActionButton.
 *
 */
@Composable
fun BottomAppBarTwoIconsAndFab(
    fabBackgroundColor: Color,
    iconFirstResName: String,
    iconSecondResName: String,
    fabIconResName: String,
    iconTintColor: Color,
    fabIconTintColor: Color,
    onIconFirstClicked: () -> Unit,
    onIconSecondClicked: () -> Unit
) {
    androidx.compose.material3.BottomAppBar(
        actions = {
            IconButton(onClick = { onIconFirstClicked() }) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, iconFirstResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = Constants.IMAGE,
                            tint = iconTintColor
                        )
                    }
            }
            IconButton(onClick = { onIconSecondClicked() }) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, iconSecondResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = Constants.IMAGE,
                            tint = iconTintColor
                        )
                    }
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
                                contentDescription = Constants.IMAGE,
                                tint = fabIconTintColor
                            )
                        }
                }
            }
        }
    )
}

/**
 * A Jetpack Compose composable that displays a styled Bottom App Bar with three customizable icons and a FloatingActionButton.
 *
 */
@Composable
fun BottomAppBarThreeIconsAndFab(
    fabBackgroundColor: Color,
    iconFirstResName: String,
    iconSecondResName: String,
    iconThirdResName: String,
    fabIconResName: String,
    iconTintColor: Color,
    fabIconTintColor: Color,
    onIconFirstClicked: () -> Unit,
    onIconSecondClicked: () -> Unit,
    onIconThirdClicked: () -> Unit
) {
    androidx.compose.material3.BottomAppBar(
        actions = {
            IconButton(onClick = { onIconFirstClicked() }) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, iconFirstResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = Constants.IMAGE,
                            tint = iconTintColor
                        )
                    }
            }
            IconButton(onClick = { onIconSecondClicked() }) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, iconSecondResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = Constants.IMAGE,
                            tint = iconTintColor
                        )
                    }
            }
            IconButton(onClick = { onIconThirdClicked() }) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, iconThirdResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = Constants.IMAGE,
                            tint = iconTintColor
                        )
                    }
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
                                contentDescription = Constants.IMAGE,
                                tint = fabIconTintColor
                            )
                        }
                }
            }
        }
    )
}

/**
 * A Jetpack Compose composable that displays a styled Bottom App Bar with four customizable icons and a FloatingActionButton.
 *
 */
@Composable
fun BottomAppBarFourIconsAndFab(
    fabBackgroundColor: Color,
    iconFirstResName: String,
    iconSecondResName: String,
    iconThirdResName: String,
    iconFourthResName: String,
    fabIconResName: String,
    iconTintColor: Color,
    fabIconTintColor: Color,
    onIconFirstClicked: () -> Unit,
    onIconSecondClicked: () -> Unit,
    onIconThirdClicked: () -> Unit,
    onIconFourthClicked: () -> Unit
) {
    androidx.compose.material3.BottomAppBar(
        actions = {
            IconButton(onClick = { onIconFirstClicked() }) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, iconFirstResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = Constants.IMAGE,
                            tint = iconTintColor
                        )
                    }
            }
            IconButton(onClick = { onIconSecondClicked() }) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, iconSecondResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = Constants.IMAGE,
                            tint = iconTintColor
                        )
                    }
            }
            IconButton(onClick = { onIconThirdClicked() }) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, iconThirdResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = Constants.IMAGE,
                            tint = iconTintColor
                        )
                    }
            }
            IconButton(onClick = { onIconFourthClicked() }) {
                LayoutUtils.getDrawableResourceId(LocalContext.current, iconFourthResName)
                    ?.let { painterResource(it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = Constants.IMAGE,
                            tint = iconTintColor
                        )
                    }
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
                                contentDescription = Constants.IMAGE,
                                tint = fabIconTintColor
                            )
                        }
                }
            }
        }
    )
}

/**
 * A Jetpack Compose composable that displays a simple bottom sheet when triggered by a Floating Action Button.
 *
 * @param toShowModal    A boolean representing whether the bottom sheet should be initially displayed.
 * @param screenContent  The content to be displayed in the main screen.
 * @param sheetContent   The content to be displayed in the bottom sheet when shown.
 *
 * Example usage:
 * ```kotlin
 * CustomizableBottomSheet(
 *     toShowModal = true, // Whether the bottom sheet should be initially displayed
 *     screenContent = { paddingValues ->
 *         // Content to be displayed in the main screen
 *     },
 *     sheetContent = {
 *         // Content to be displayed in the bottom sheet when shown
 *     }
 * )
 * ```
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleBottomSheet(
    toShowModal: Boolean,
    screenContent: @Composable (PaddingValues) -> Unit,
    sheetContent: @Composable () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scaffoldState = rememberScaffoldState()
    var showBottomSheet by remember { mutableStateOf(toShowModal) }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = Constants.ICON) },
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
 * Composable function to create a styled app layout with a top bar comprised of two icon buttons and a title and a bottom bar that consists of four icon buttons and a floating action button.
 *
 * @param topBarTitle                   The title for the top app bar.
 * @param topBarColor                   The background color for the top app bar.
 * @param topBarTitleColor              The color for the title text in the top app bar.
 * @param topBarStartIconResName        The resource name of the start icon in the top app bar.
 * @param topBarStartIconTintColor      The tint color for the start icon in the top app bar.
 * @param topBarEndIconResName          The resource name of the end icon in the top app bar.
 * @param topBarEndIconTintColor        The tint color for the end icon in the top app bar.
 * @param onTopBarStartIconClicked      Lambda to be executed when the start icon is clicked.
 * @param onTopBarEndIconClicked        Lambda to be executed when the end icon is clicked.
 * @param fabBackgroundColor            The background color for the floating action button (FAB).
 * @param bottomIconFirstResName        The resource name of the first icon in the bottom bar.
 * @param bottomIconSecondResName       The resource name of the second icon in the bottom bar.
 * @param bottomIconThirdResName        The resource name of the third icon in the bottom bar.
 * @param bottomIconFourthResName       The resource name of the fourth icon in the bottom bar.
 * @param fabIconResName                The resource name of the FAB icon.
 * @param bottomIconTintColor           The tint color for the icons in the bottom bar.
 * @param fabIconTintColor              The tint color for the FAB icon.
 * @param onBottomIconFirstClicked      Lambda to be executed when the first icon in the bottom bar is clicked.
 * @param onBottomIconSecondClicked     Lambda to be executed when the second icon in the bottom bar is clicked.
 * @param onBottomIconThirdClicked      Lambda to be executed when the third icon in the bottom bar is clicked.
 * @param onBottomIconFourthClicked     Lambda to be executed when the fourth icon in the bottom bar is clicked.
 * @param screenContent                 The content to be displayed within the layout.
 *
 * This Composable function creates a styled layout with a top bar and a bottom bar. The top bar
 * can have a title, start icon, and end icon, while the bottom bar contains four icons and a
 * floating action button (FAB).
 *
 * Example usage:
 * ```kotlin
 * StyledBarLayoutWithFab(
 *     topBarTitle = "App Title",
 *     topBarColor = Color.Blue,
 *     topBarTitleColor = Color.White,
 *     // ... (other parameters)
 * ) { paddingValues ->
 *     // Screen content goes here
 * }
 * ```
 */
@Composable
fun StyledBarLayoutWithFab(
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
    bottomIconFirstResName: String,
    bottomIconSecondResName: String,
    bottomIconThirdResName: String,
    bottomIconFourthResName: String,
    fabIconResName: String,
    bottomIconTintColor: Color,
    fabIconTintColor: Color,
    onBottomIconFirstClicked: () -> Unit,
    onBottomIconSecondClicked: () -> Unit,
    onBottomIconThirdClicked: () -> Unit,
    onBottomIconFourthClicked: () -> Unit,
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
            BottomAppBarFourIconsAndFab(
                fabBackgroundColor = fabBackgroundColor,
                iconFirstResName = bottomIconFirstResName,
                iconSecondResName = bottomIconSecondResName,
                iconThirdResName = bottomIconThirdResName,
                iconFourthResName = bottomIconFourthResName,
                fabIconResName = fabIconResName,
                iconTintColor = bottomIconTintColor,
                fabIconTintColor = fabIconTintColor,
                onIconFirstClicked = { onBottomIconFirstClicked() },
                onIconSecondClicked = { onBottomIconSecondClicked() },
                onIconThirdClicked = { onBottomIconThirdClicked() },
                onIconFourthClicked = {onBottomIconFourthClicked () }
            )
        }
    ) { paddingValues ->
        screenContent(paddingValues)
    }
}

