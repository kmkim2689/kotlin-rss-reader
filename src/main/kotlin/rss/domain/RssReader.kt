package rss.domain

interface RssReader {
    suspend fun updatedBlogs(
        count: Int,
        sortBy: Sort,
    ): List<PostCollection>
}


