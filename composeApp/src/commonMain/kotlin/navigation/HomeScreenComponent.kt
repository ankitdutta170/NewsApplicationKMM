package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import network.model.Article
import network.model.NewsResponse

class HomeScreenComponent(
    componentContext: ComponentContext,
    private val navigation: StackNavigation<RootComponent.Configuration>,
    private val onNavigateToDetailScreen: (Article) -> Unit
): ComponentContext by componentContext {



    private var _newsArticle = MutableValue(Article())
    val article: Value<Article> = _newsArticle


    fun onEvent(event: HomeScreenEvent){
        when(event){
            is HomeScreenEvent.OnNewsItemClicked -> onNavigateToDetailScreen(article.value)
            is HomeScreenEvent.OnUpdateArticle -> {
                _newsArticle.value = event.article
            }
            is HomeScreenEvent.OnBackClicked -> {
                navigation.pop()
            }

            else -> {}
        }
    }
}