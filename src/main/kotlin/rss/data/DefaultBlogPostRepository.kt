package rss.data

import rss.domain.BlogPost
import rss.domain.BlogPostRepository

class DefaultBlogPostRepository : BlogPostRepository {
    override suspend fun postsByUrl(
        url: String,
        count: Int,
    ): List<BlogPost> {
        return emptyList()
    }
}
