package rss.controller

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import rss.domain.Sort
import rss.domain.reader.DefaultRssReader
import rss.domain.repository.BlogsRepository
import rss.view.BlogContentOutputView
import rss.view.KeywordSearchInputView
import rss.view.KeywordSearchOutputView
import rss.view.LoadingView
import rss.view.UrlInputView

class ConsoleRssReaderController(
    private val blogsRepository: BlogsRepository,
    private val scope: CoroutineScope,
) {
    private val urls by lazy {
        UrlInputView.urls()
    }
    private val reader by lazy {
        DefaultRssReader(blogsRepository = blogsRepository, blogUrls = urls)
    }
    private lateinit var searchingJob: Job

    suspend fun start() {
        reader.addUrl(*urls.toTypedArray())
        updateBlogContents()
        launchLoadingContents()
        launchSearchingKeyword()
    }

    private fun launchLoadingContents() {
        scope.launch {
            while (true) {
                delay(LOADING_INTERVAL_MILLISECONDS)
                searchingJob.cancel()
                updateBlogContents()
                launchSearchingKeyword()
            }
        }
    }

    private fun launchSearchingKeyword() {
        searchingJob =
            scope.launch {
                while (isActive) {
                    val keyword = KeywordSearchInputView.keyword()
                    val posts = reader.postsWithKeyword(keyword, DEFAULT_COUNT)
                    KeywordSearchOutputView.logContentsByKeyword(keyword, posts)
                }
            }
    }

    private suspend fun updateBlogContents() {
        LoadingView.showLoading()
        reader.updateBlogs(DEFAULT_COUNT, Sort.LATEST)
        BlogContentOutputView.logContents(reader.blogs)
    }

    companion object {
        private const val DEFAULT_COUNT = 10
        private const val LOADING_INTERVAL_MILLISECONDS = 60000L
    }
}
