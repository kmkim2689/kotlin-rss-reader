package rss.domain

interface Post {
    fun contains(keyword: String): Boolean
}
