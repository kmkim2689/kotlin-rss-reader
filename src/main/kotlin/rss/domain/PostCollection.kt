package rss.domain

interface PostCollection {
    suspend fun update(newPosts: List<Post>)
}
