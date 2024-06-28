package rss.data.model

data class BlogResponse(
    val title: String,
    val link: String,
    val description: String,
    val lastBuildDate: String,
    val imageResponse: ImageResponse?,
    val items: List<BlogPostResponse>,
)
