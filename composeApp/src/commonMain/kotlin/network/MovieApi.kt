import AppConstant.NEWS_API_KEY
import AppConstant.NEWS_BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import network.model.MovieResponse
import network.model.NewsResponse


class MovieApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
                coerceInputValues = true
            })
        }
    }

    suspend fun getNews(): NewsResponse? {
        return httpClient.get("${NEWS_BASE_URL}&apiKey=${NEWS_API_KEY}").body()
    }

}