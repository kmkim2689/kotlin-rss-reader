package rss.domain

class BlogPosts(
    private var values: List<BlogPost> = emptyList(),
) {
    init {
        arrange()
    }

    fun posts(count: Int = DEFAULT_POST_COUNT): List<BlogPost> = values.take(count)

    private fun arrange() {
        values = values.sortedByDescending { it.metaData.pubDate }
    }

    companion object {
        private const val DEFAULT_POST_COUNT = 10
    }
}
