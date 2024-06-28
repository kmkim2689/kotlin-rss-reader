package rss.domain.collection

import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import rss.domain.collection.Blog
import rss.domain.post.BlogPost
import rss.domain.post.MetaData
import java.time.LocalDateTime

class BlogTest {
    @Test
    fun `게시글을 업데이트할 수 있다`() =
        runTest {
            // given
            val blog = Blog(
                title = "kmkim",
                link = "https://velog.io/@kmkim2689",
                description = "kmkim1",
                lastBuildDate = LocalDateTime.of(2024, 6, 1, 1, 1, 1),
                imageResponse = null,
                items = emptyList(),
            )

            // when
            repeat(2) {
                blog.update(postFixtures(it + 1, 10))
            }

            // then
            assertThat(blog.posts.contents()).isEqualTo(
                listOf(
                    BlogPost(
                        metaData =
                        MetaData(
                            title = "게시글11",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-12T01:01:01"),
                        ),
                        content = "게시글 내용11",
                    ),
                    BlogPost(
                        metaData =
                        MetaData(
                            title = "게시글10",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-11T01:01:01"),
                        ),
                        content = "게시글 내용10",
                    ),
                    BlogPost(
                        metaData =
                        MetaData(
                            title = "게시글9",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-10T01:01:01"),
                        ),
                        content = "게시글 내용9",
                    ),
                    BlogPost(
                        metaData =
                        MetaData(
                            title = "게시글8",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-09T01:01:01"),
                        ),
                        content = "게시글 내용8",
                    ),
                    BlogPost(
                        metaData =
                        MetaData(
                            title = "게시글7",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-08T01:01:01"),
                        ),
                        content = "게시글 내용7",
                    ),
                    BlogPost(
                        metaData =
                        MetaData(
                            title = "게시글6",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-07T01:01:01"),
                        ),
                        content = "게시글 내용6",
                    ),
                    BlogPost(
                        metaData =
                        MetaData(
                            title = "게시글5",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-06T01:01:01"),
                        ),
                        content = "게시글 내용5",
                    ),
                    BlogPost(
                        metaData =
                        MetaData(
                            title = "게시글4",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-05T01:01:01"),
                        ),
                        content = "게시글 내용4",
                    ),
                    BlogPost(
                        metaData =
                        MetaData(
                            title = "게시글3",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-04T01:01:01"),
                        ),
                        content = "게시글 내용3",
                    ),
                    BlogPost(
                        metaData =
                        MetaData(
                            title = "게시글2",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-03T01:01:01"),
                        ),
                        content = "게시글 내용2",
                    ),
                ),
            )
        }

    @ParameterizedTest
    @ValueSource(strings = ["velog.io/@kmkim2689", "file://velog.io/", "http://velog.io/"])
    fun `블로그 주소가 올바르지 않은 프로토콜을 가지면 예외가 발생한다`(blogUrl: String) {
        assertThrows<IllegalArgumentException> {
            Blog("kmkim", blogUrl, "", LocalDateTime.now(), null, emptyList())
        }
    }

    private fun postFixtures(
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
