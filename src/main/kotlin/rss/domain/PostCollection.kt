package rss.domain

interface PostCollection {
    suspend fun update(
//        url: String,
//        count: Int,
        newPosts: List<Post>
    )
}
