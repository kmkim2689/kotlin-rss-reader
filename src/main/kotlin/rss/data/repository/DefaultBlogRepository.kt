package rss.data.repository

import rss.data.datasource.BlogRemoteDataSource
import rss.data.util.toBlog
import rss.domain.Sort
import rss.domain.collection.Blog
import rss.domain.repository.BlogRepository

class DefaultBlogRepository(
    private val blogRemoteDataSource: BlogRemoteDataSource,
) : BlogRepository {
    override suspend fun blog(
        url: String,
        count: Int,
        sort: Sort,
    ): Result<Blog> {
        return runCatching {
            blogRemoteDataSource
                .postsByUrl(url)
                .toBlog(count)
        }
    }
}
