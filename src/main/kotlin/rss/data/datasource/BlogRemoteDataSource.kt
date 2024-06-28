package rss.data.datasource

import rss.data.model.BlogResponse

interface BlogRemoteDataSource {
    suspend fun postsByUrl(url: String): BlogResponse
}
