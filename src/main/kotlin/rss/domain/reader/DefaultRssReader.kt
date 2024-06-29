package rss.domain.reader

import rss.domain.Sort
import rss.domain.UpdateStatus
import rss.domain.collection.Blog
import rss.domain.post.Post
import rss.domain.repository.BlogsRepository

data class DefaultRssReader(
    private val blogsRepository: BlogsRepository,
    val blogUrls: List<String> = emptyList(),
) : RssReader {
    private var _blogs = emptyList<Blog>()
    val blogs: List<Blog>
        get() = _blogs

    fun addUrl(vararg url: String): DefaultRssReader = copy(blogUrls = blogUrls + url)

    override suspend fun initialize(
        count: Int,
        sortBy: Sort,
    ) {
        _blogs = blogs(count, sortBy)
    }

    override suspend fun update(
        count: Int,
        sortBy: Sort,
    ): Result<UpdateStatus> {
        val fetchedBlogs = blogs(count, sortBy)
        val updated = updatedOrNot(fetchedBlogs)
        if (!updated) return Result.success(UpdateStatus.UP_TO_DATE)
        _blogs = fetchedBlogs
        return Result.success(UpdateStatus.UPDATED)
    }

    override fun postsWithKeyword(
        keyword: String,
        count: Int,
    ): List<Post> {
        val posts =
            blogs
                .map(Blog::posts)
                .map { it.contents(count) }
                .flatten()

        return posts.filter { keyword in it.metaData.title || keyword in it.content }
    }

    private fun updatedOrNot(fetchedBlogs: List<Blog>): Boolean {
        return blogs
            .zip(fetchedBlogs) { oldValue, newValue ->
                oldValue.lastBuildDate != newValue.lastBuildDate
            }.contains(true)
    }

    private suspend fun blogs(
        count: Int,
        sortBy: Sort,
    ): List<Blog> {
        return blogsRepository.blogs(
            urls = blogUrls,
            count = count,
            sort = sortBy,
        )
    }
}
