package rss.data.model

import rss.domain.BlogPost
import rss.domain.MetaData
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class BlogPostResponse(
    val title: String,
    val link: String,
    val pubDate: String,
    val description: String,
)
