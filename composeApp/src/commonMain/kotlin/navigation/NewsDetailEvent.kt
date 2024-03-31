package navigation

sealed interface NewsDetailsUiEvent {
    data object OnBackClicked : NewsDetailsUiEvent
}