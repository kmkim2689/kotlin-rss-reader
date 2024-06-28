package rss.domain

import rss.data.model.ImageResponse
import java.time.LocalDateTime

class Blog(
    val title: String,
    val link: String,
    val description: String,
    val lastBuildDate: LocalDateTime,
    val imageResponse: ImageResponse?,
    items: List<BlogPost>,
) : PostCollection {
    var posts: BlogPosts = BlogPosts(items)
        private set

    init {
        require(link.contains(VALID_PROTOCOL)) { EXCEPTION_INVALID_PROTOCOL }
    }

    override suspend fun update(newPosts: List<Post>) {
        posts = BlogPosts(newPosts)
    }

    companion object {
        private const val VALID_PROTOCOL = "https://"
        private const val EXCEPTION_INVALID_PROTOCOL = "올바르지 않은 블로그 주소 형태입니다."
    }
}
