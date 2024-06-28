package rss.data

import rss.domain.BlogPost
import rss.domain.BlogPostRepository
import rss.domain.MetaData
import java.time.LocalDateTime

class FakeBlogPostRepository : BlogPostRepository {
    private var trial = 1

    override suspend fun postsByUrl(
        url: String,
        count: Int,
    ): List<BlogPost> {
        return posts(trial++, count)
    }

    private fun posts(
        trial: Int,
        count: Int,
    ): List<BlogPost> {
        return List(count) {
            BlogPost(
                metaData =
                    MetaData(
                        title = "게시글${trial + it}",
                        postUrl = "https://velog.io/@kmkim2689",
                        pubDate = LocalDateTime.of(2024, 6, 1, 1, 1, 1).plusDays(trial.toLong() + it.toLong()),
                    ),
                content = "게시글 내용${trial + it}",
            )
        }
    }
}
