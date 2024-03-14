package network.model



import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(

    val page: Int?,

    val results: List<Result?>?,

    val totalPages: Int?,

    val totalResults: Int?
)