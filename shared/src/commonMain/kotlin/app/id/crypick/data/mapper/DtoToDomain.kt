package app.id.crypick.data.mapper

import app.id.crypick.data.network.dto.NewsDto
import app.id.crypick.domain.model.News
import app.id.crypick.utils.DateTimeKtx

fun NewsDto.toDomain(dateTimeKtx: DateTimeKtx): List<News> {
    return this.articles.map {
        News(
            author = it.author.orEmpty(),
            title = it.title.orEmpty(),
            description = it.description.orEmpty(),
            publishedAt = it.publishedAt?.let { date ->
                dateTimeKtx.getFormattedDate(date)
            }.orEmpty(),
            url = it.url.orEmpty(),
            imgUrl = it.urlToImage.orEmpty(),
            content = it.content.orEmpty()
        )
    }
}