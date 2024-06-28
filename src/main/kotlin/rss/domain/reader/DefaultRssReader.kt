package rss.domain.reader

import rss.domain.Sort
import rss.domain.collection.Blog
import rss.domain.repository.BlogsRepository

data class DefaultRssReader(
    private val blogsRepository: BlogsRepository,
    val blogUrls: List<String> = emptyList(),
) {
    fun addUrl(vararg url: String): DefaultRssReader =
        copy(blogUrls = blogUrls + url)

    suspend fun updatedBlogs(
        count: Int,
        sortBy: Sort,
    ): List<Blog> {
        return blogsRepository.blogs(
            urls = blogUrls,
            count = count,
            sort = sortBy,
        ).getOrThrow()
    }
}
