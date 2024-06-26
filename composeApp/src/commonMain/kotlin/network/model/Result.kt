package network.model

import AppConstant.BASE_URL
import AppConstant.POSTER_BASE_URL
import kotlinx.serialization.Serializable

@Serializable
data class Result(

    val adult: Boolean?,

    val backdropPath: String?,

    val genreIds: List<Int?>?,

    val id: Int?,

    val originalLanguage: String?,

    val originalTitle: String?,

    val overview: String?,

    val popularity: Double?,

    val posterPath: String?,

    val releaseDate: String?,

    val title: String?,

    val video: Boolean?,

    val voteAverage: Double?,

    val voteCount: Int?
){
    fun getImagePath() = BASE_URL + posterPath
}