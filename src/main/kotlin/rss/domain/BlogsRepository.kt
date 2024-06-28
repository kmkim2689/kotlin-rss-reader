package rss.domain

interface BlogsRepository {
    suspend fun blogs(
        urls: List<String>,
        count: Int,
        sort: Sort
    ): Result<List<Blog>>
}
