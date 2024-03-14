package navigation

import network.model.Article

sealed interface HomeScreenEvent {

    data class OnNewsItemClicked(val article: Article):HomeScreenEvent

    data class OnUpdateArticle(val article: Article):HomeScreenEvent

}