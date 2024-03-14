package network.model



import AppConstant.NEWS_BASE_URL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(

    @SerialName("content")val content: String?=null,
    @SerialName("author")val author: String?=null,
    @SerialName("description")val description: String?=null,
    @SerialName("publishedAt")val publishedAt: String?=null,
    @SerialName("source")val source: Source?=null,
    @SerialName("title")val title: String?=null,
    @SerialName("url")val url: String?=null,
    @SerialName("urlToImage")val urlToImage: String?=null,
){
    fun getImageUrl() = NEWS_BASE_URL + urlToImage
}