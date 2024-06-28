package rss.data

import rss.domain.BlogPost
import rss.domain.BlogRepository
import rss.domain.MetaData
import rss.domain.Post
import rss.domain.Sort
import java.time.LocalDateTime

class FakeBlogRepository : BlogRepository {
    private var trial = 1

    override suspend fun postsByUrl(url: String, count: Int, sort: Sort): List<Post> {
        return posts(trial, count)
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
