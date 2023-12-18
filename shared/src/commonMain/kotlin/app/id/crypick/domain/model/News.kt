package app.id.crypick.domain.model

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver


data class News(
    val author: String,
    val title: String,
    val description: String,
    val publishedAt: String,
    val url: String,
    val imgUrl: String,
    val content: String
)

val newsSaver: Saver<News, Any> = run {
    val authorKey = "author"
    val titleKey = "title"
    val descriptionKey = "description"
    val publishedAtKey = "publishedAt"
    val urlKey = "url"
    val imgUrlKey = "imgUrl"
    val contentKey = "content"
    mapSaver(
        save = { mapOf(
            authorKey to it.author,
            titleKey to it.title,
            descriptionKey to it.description,
            publishedAtKey to it.publishedAt,
            urlKey to it.url,
            imgUrlKey to it.imgUrl,
            contentKey to it.content
        ) },
        restore = { News(
            it[authorKey] as String, it[titleKey] as String,
            it[descriptionKey] as String, it[publishedAtKey] as String,
            it[urlKey] as String, it[imgUrlKey] as String,
            it[contentKey] as String
        ) }
    )
}