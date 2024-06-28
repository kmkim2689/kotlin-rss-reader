package rss.domain

interface BlogRepository {
    suspend fun postsByUrl(
        url: String,
        count: Int,
        sort: Sort
    ): List<Post>
}
