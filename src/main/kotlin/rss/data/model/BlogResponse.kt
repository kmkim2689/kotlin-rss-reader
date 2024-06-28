package rss.data.model

data class BlogResponse(
    val title: String,
    val link: String,
    val description: String,
    val lastBuildDate: String,
    val image: Image?,
    val items: List<BlogPostResponse>,
)

