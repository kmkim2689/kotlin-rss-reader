package rss.domain

data class BlogPost(
    val metaData: MetaData,
    val content: String,
) : Post {
    override fun contains(keyword: String): Boolean {
        val trimmedKeyword = keyword.trim()
        return trimmedKeyword in metaData.title || trimmedKeyword in content
    }
}
