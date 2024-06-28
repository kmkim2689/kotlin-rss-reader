package rss.domain

interface BlogPostRepository {
    suspend fun postsByUrl(
        url: String,
        count: Int,
    ): List<BlogPost>
}

