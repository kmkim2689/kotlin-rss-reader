package rss.data.util

import rss.data.model.BlogPostResponse
import rss.domain.BlogPost
import rss.domain.MetaData
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun BlogPostResponse.toBlogPost(
    dateTimePattern: String = "EEE, dd MMM yyyy HH:mm:ss z",
    dateTimeLocale: Locale = Locale.ENGLISH,
): BlogPost {
    val formatter = DateTimeFormatter.ofPattern(dateTimePattern, dateTimeLocale)
    val zonedDateTime = ZonedDateTime.parse(pubDate, formatter)
    val localDateTime = zonedDateTime.toLocalDateTime()

    return BlogPost(
        metaData = MetaData(
            title = title,
            postUrl = link,
            pubDate = localDateTime
        ),
        content = description,
    )
}
