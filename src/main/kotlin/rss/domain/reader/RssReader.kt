package rss.domain.reader

import rss.domain.Sort
import rss.domain.UpdateStatus
import rss.domain.post.Post

interface RssReader {
    suspend fun initialize(
        count: Int,
        sortBy: Sort,
    )

    fun postsWithKeyword(
        keyword: String,
        count: Int,
    ): List<Post>

    suspend fun update(count: Int, sortBy: Sort): Result<UpdateStatus>
}
