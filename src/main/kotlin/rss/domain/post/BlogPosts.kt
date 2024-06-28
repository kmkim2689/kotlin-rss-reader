package rss.domain.post

class BlogPosts(
    private var values: List<Post> = emptyList(),
) {
    init {
        arrange()
    }

    fun contents(count: Int = DEFAULT_POST_COUNT): List<Post> = values.take(count)

    private fun arrange() {
        values = values.sortedByDescending { it.metaData.pubDate }
    }

    companion object {
        private const val DEFAULT_POST_COUNT = 10
    }
}
