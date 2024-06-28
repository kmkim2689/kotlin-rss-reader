package rss.data.util

import rss.data.model.BlogPostResponse
import rss.data.model.BlogResponse
import rss.domain.collection.Blog
import rss.domain.post.BlogPost
import rss.domain.post.MetaData
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun BlogResponse.toBlog(count: Int): Blog =
    Blog(
        title = title,
        link = link,
        description = description,
        lastBuildDate = lastBuildDate.toLocalDateTime(),
        imageResponse = imageResponse,
        items = items.map(BlogPostResponse::toBlogPost).sortedByDescending { it.metaData.pubDate }.take(count),
    )

fun BlogPostResponse.toBlogPost(): BlogPost {
    val localDateTime = pubDate.toLocalDateTime()

    return BlogPost(
        metaData = MetaData(
            title = title,
            postUrl = link,
            pubDate = localDateTime
        ),
        content = description,
    )
}

private fun String.toLocalDateTime(
    dateTimePattern: String = "EEE, dd MMM yyyy HH:mm:ss z",
    dateTimeLocale: Locale = Locale.ENGLISH,
): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern(dateTimePattern, dateTimeLocale)
    val zonedDateTime = ZonedDateTime.parse(this, formatter)
    val localDateTime = zonedDateTime.toLocalDateTime()
    return localDateTime
}
