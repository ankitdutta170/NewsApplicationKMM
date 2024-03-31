package navigation

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import network.model.Article

class NewsDetailScreenComponentt(
    val article: Article,
    private val navigation: StackNavigation<RootComponent.Configuration>,
    componentContext: ComponentContext)
    :ComponentContext by componentContext{

    val state = mutableStateOf(article)

    fun onEvent(event: NewsDetailsUiEvent) {
        when (event) {
            is NewsDetailsUiEvent.OnBackClicked -> {
                navigation.pop()
            }
        }
    }
}