package rss.domain.reader

import rss.domain.Sort
import rss.domain.collection.PostCollection

interface RssReader {
    suspend fun updatedBlogs(
        count: Int,
        sortBy: Sort,
    ): List<PostCollection>
}


