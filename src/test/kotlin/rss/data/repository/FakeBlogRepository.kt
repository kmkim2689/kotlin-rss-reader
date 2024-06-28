package rss.data.repository

import rss.domain.Sort
import rss.domain.collection.Blog
import rss.domain.post.BlogPost
import rss.domain.post.MetaData
import rss.domain.repository.BlogRepository
import java.time.LocalDateTime

class FakeBlogRepository : BlogRepository {
    private var trial = 1
    private var blogCount = 0

    override suspend fun blog(
        url: String,
        count: Int,
        sort: Sort,
    ): Result<Blog> {
        return runCatching {
            blogCount++
            Blog(
                title = "blog $blogCount",
                link = "https://blog$blogCount.com",
                description = "description $blogCount",
                lastBuildDate = LocalDateTime.now(),
                imageResponse = null,
                items = posts(trial, count),
            )
        }
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
                        pubDate =
                            LocalDateTime
                                .of(2024, 6, 1, 1, 1, 1)
                                .plusDays(trial.toLong() + it.toLong()),
                    ),
                content = "게시글 내용${trial + it}",
            )
        }
    }
}
