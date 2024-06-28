package rss.domain

interface Post {
    val metaData: MetaData
    val content: String

    fun contains(keyword: String): Boolean
}
