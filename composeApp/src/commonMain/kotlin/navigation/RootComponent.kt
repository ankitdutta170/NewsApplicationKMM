package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable
import network.model.Article

class RootComponent(
    componentComponent: ComponentContext
) : ComponentContext by componentComponent {

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.HomeScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when (config) {
            is Configuration.HomeScreen -> Child.HomeScreen(
                HomeScreenComponent(
                    componentContext = context,
                    onNavigateToDetailScreen = { article ->
                        println("News item clicked ${article.title}")
                        navigation.pushNew(Configuration.NewsDetailScreen(article))
                    })
            )

            is Configuration.NewsDetailScreen -> Child.NewsDetailScreen(
                NewsDetailScreenComponentt(config.article, context)
            )
        }
    }

    sealed class Child {
        data class HomeScreen(val component: HomeScreenComponent) : Child()
        data class NewsDetailScreen(val component: NewsDetailScreenComponentt) : Child()
    }


    @Serializable
    sealed class Configuration {
        @Serializable
        data object HomeScreen : Configuration()

        @Serializable
        data class NewsDetailScreen(val article: Article) : Configuration()
    }

}