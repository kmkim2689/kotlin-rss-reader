package rss.domain

import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import rss.data.FakeBlogPostRepository
import java.time.LocalDateTime

class BlogTest {
    @Test
    fun `게시글을 업데이트할 수 있다`() =
        runTest {
            // given
            val blog = Blog("https://velog.io/@kmkim2689")
            val fakeBlogPostRepository = FakeBlogPostRepository()

            // when
            repeat(2) {
                blog.update(fakeBlogPostRepository.postsByUrl("https://velog.io/@kmkim2689", 10))
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
}
