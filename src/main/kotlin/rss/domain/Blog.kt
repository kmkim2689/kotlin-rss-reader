package rss.domain

class Blog(
    val blogUrl: String,
) : PostCollection {
    var posts: BlogPosts = BlogPosts()
        private set

    init {
        require(blogUrl.contains(VALID_PROTOCOL)) { EXCEPTION_INVALID_PROTOCOL }
    }

    override suspend fun update(newPosts: List<Post>) {
        posts = BlogPosts(newPosts)
    }

    companion object {
        private const val VALID_PROTOCOL = "https://"
        private const val EXCEPTION_INVALID_PROTOCOL = "올바르지 않은 블로그 주소 형태입니다."
    }
}
