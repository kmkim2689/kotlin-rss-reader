package rss

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import rss.controller.ConsoleRssReaderController
import rss.data.datasource.DefaultBlogRemoteDataSource
import rss.data.repository.DefaultBlogRepository
import rss.data.repository.DefaultBlogsRepository

suspend fun main() = coroutineScope<Unit> {
    launch {
        ConsoleRssReaderController(
            DefaultBlogsRepository(DefaultBlogRepository(DefaultBlogRemoteDataSource()), this),
            this,
        ).start()
    }
}
