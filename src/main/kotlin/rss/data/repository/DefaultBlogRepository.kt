package rss.data.repository

import rss.data.datasource.BlogRemoteDataSource
import rss.data.model.BlogPostResponse
import rss.data.util.toBlogPost
import rss.domain.BlogPost
import rss.domain.BlogRepository
import rss.domain.Post
import rss.domain.Sort

class DefaultBlogRepository(
    private val blogRemoteDataSource: BlogRemoteDataSource
) : BlogRepository {
    override suspend fun postsByUrl(
        url: String,
        count: Int,
        sort: Sort,
    ): List<Post> {
        val posts = blogRemoteDataSource
            .postsByUrl(url)
            .items
            .map(BlogPostResponse::toBlogPost)

        return sortedPosts(sort, posts).take(count)
    }

    private fun sortedPosts(
        sort: Sort,
        posts: List<BlogPost>,
    ) = when (sort) {
        Sort.LATEST -> posts.sortedByDescending { it.metaData.pubDate }
        Sort.OLDEST -> posts.sortedBy { it.metaData.pubDate }
    }
}
