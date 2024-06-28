package rss.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import rss.domain.Blog
import rss.domain.BlogsRepository
import rss.domain.Sort

class FakeBlogsRepository(
    private val scope: CoroutineScope,
    private val blogRepository: FakeBlogRepository = FakeBlogRepository(),
) : BlogsRepository {
    override suspend fun blogs(urls: List<String>, count: Int, sort: Sort): Result<List<Blog>> {
        return runCatching {
            urls
                .map {
                    blog(it, count, sort)
                }
                .awaitAll()
                .filterNotNull()
        }
    }

    private fun blog(
        url: String,
        count: Int,
        sort: Sort,
    ) = scope.async {
        blogRepository.blog(url, count, sort).getOrNull()
    }
}
