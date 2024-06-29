package rss.domain.reader

import rss.domain.Sort
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

    override suspend fun updateBlogs(
        count: Int,
        sortBy: Sort,
    ) {
        _blogs =
            blogsRepository.blogs(
                urls = blogUrls,
                count = count,
                sort = sortBy,
            )
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
}
