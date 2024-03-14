import network.model.MovieResponse
import network.model.NewsResponse

class MovieSDK {
    private val movieApi = MovieApi()

    @Throws(Exception::class)
    suspend fun getPopularMovies(): NewsResponse {
        val popularMovieResponse = movieApi.getNews()
        return if (popularMovieResponse != null) {
            popularMovieResponse
        } else {
            throw NullPointerException()
        }
    }
}