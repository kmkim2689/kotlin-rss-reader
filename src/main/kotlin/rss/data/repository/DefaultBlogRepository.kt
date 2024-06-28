package rss.data.repository

import rss.data.datasource.BlogRemoteDataSource
import rss.data.model.BlogPostResponse
import rss.data.model.BlogResponse
import rss.data.util.toBlog
import rss.data.util.toBlogPost
import rss.domain.Blog
import rss.domain.BlogPost
import rss.domain.BlogRepository
import rss.domain.Post
import rss.domain.PostCollection
import rss.domain.Sort

class DefaultBlogRepository(
    private val blogRemoteDataSource: BlogRemoteDataSource
) : BlogRepository {
    override suspend fun blog(
        url: String,
        count: Int,
        sort: Sort,
    ): Result<Blog> {
        return kotlin.runCatching {
            blogRemoteDataSource
                .postsByUrl(url)
                .toBlog(count)
        }
    }
}
