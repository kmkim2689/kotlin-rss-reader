package rss.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import rss.domain.collection.Blog
import rss.domain.repository.BlogRepository
import rss.domain.repository.BlogsRepository
import rss.domain.Sort

class DefaultBlogsRepository(
    private val blogRepository: BlogRepository,
    private val scope: CoroutineScope = CoroutineScope(Job() + Dispatchers.IO),
) : BlogsRepository {
    override suspend fun blogs(
        urls: List<String>,
        count: Int,
        sort: Sort,
    ): Result<List<Blog>> =
        runCatching {
            urls
                .map {
                    blog(it, count, sort)
                }
                .awaitAll()
                .filterNotNull()
        }

    private fun blog(
        url: String,
        count: Int,
        sort: Sort,
    ) = scope.async {
        blogRepository.blog(url, count, sort).getOrNull()
    }
}

