package navigation

import com.arkivanov.decompose.ComponentContext
import network.model.Article

class NewsDetailScreenComponentt(
    val article: Article,
    componentContext: ComponentContext)
    :ComponentContext by componentContext{
}