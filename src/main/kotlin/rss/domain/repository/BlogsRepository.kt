package rss.domain.repository

import rss.domain.Sort
import rss.domain.collection.Blog

interface BlogsRepository {
    suspend fun blogs(
        urls: List<String>,
        count: Int,
        sort: Sort,
    ): List<Blog>
}
