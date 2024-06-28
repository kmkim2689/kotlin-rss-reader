package rss.domain.collection

import rss.domain.post.Post

interface PostCollection {
    suspend fun update(newPosts: List<Post>)
}
