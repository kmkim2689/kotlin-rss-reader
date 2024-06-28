package rss.domain

interface BlogRepository {
    suspend fun blog(
        url: String,
        count: Int,
        sort: Sort
    ): Result<Blog>
}
