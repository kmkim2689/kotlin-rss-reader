package rss.domain.repository

import rss.domain.Sort
import rss.domain.collection.Blog

interface BlogRepository {
    suspend fun blog(
        url: String,
        count: Int,
        sort: Sort
    ): Result<Blog>
}
