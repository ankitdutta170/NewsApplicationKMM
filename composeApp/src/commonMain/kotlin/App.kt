import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.getValue
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import navigation.HomeScreenEvent
import navigation.NewsDetailsUiEvent
import navigation.RootComponent
import network.model.NewsResponse
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import newsapplicationkmm.composeapp.generated.resources.Res
import newsapplicationkmm.composeapp.generated.resources.compose_multiplatform
import org.example.project.HomeScreen
import org.example.project.NewsDetailScreen


private val mainScope = MainScope()

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App(rootComponent: RootComponent) {

    MaterialTheme {
        val childStack by rootComponent.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.HomeScreen -> HomeScreen(
                    component = instance.component,
                    onBackClick = { instance.component.onEvent(HomeScreenEvent.OnBackClicked) },
                    onEvent = { instance.component.onEvent(it) }
                )

                is RootComponent.Child.NewsDetailScreen -> NewsDetailScreen(
                    article = instance.component.article,
                    onBackClick = {instance.component.onEvent(NewsDetailsUiEvent.OnBackClicked)},
                    component = instance.component
                )
            }

        }

    }


}