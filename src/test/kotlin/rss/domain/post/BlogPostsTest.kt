package rss.domain.post

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class BlogPostsTest {
    @Test
    fun `블로그 게시글이 주어지면 최신순으로 정렬된다`() {
        // given
        val posts = blogPostFixtures(10).shuffled()

        // when
        val blogPosts = BlogPosts(posts)

        // then
        assertThat(blogPosts.contents(10)).isEqualTo(
            listOf(
                BlogPost(
                    metaData =
                        MetaData(
                            title = "게시글10",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-10T01:01:01"),
                        ),
                    content = "게시글 내용10",
                ),
                BlogPost(
                    metaData =
                        MetaData(
                            title = "게시글9",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-09T01:01:01"),
                        ),
                    content = "게시글 내용9",
                ),
                BlogPost(
                    metaData =
                        MetaData(
                            title = "게시글8",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-08T01:01:01"),
                        ),
                    content = "게시글 내용8",
                ),
                BlogPost(
                    metaData =
                        MetaData(
                            title = "게시글7",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-07T01:01:01"),
                        ),
                    content = "게시글 내용7",
                ),
                BlogPost(
                    metaData =
                        MetaData(
                            title = "게시글6",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-06T01:01:01"),
                        ),
                    content = "게시글 내용6",
                ),
                BlogPost(
                    metaData =
                        MetaData(
                            title = "게시글5",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-05T01:01:01"),
                        ),
                    content = "게시글 내용5",
                ),
                BlogPost(
                    metaData =
                        MetaData(
                            title = "게시글4",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-04T01:01:01"),
                        ),
                    content = "게시글 내용4",
                ),
                BlogPost(
                    metaData =
                        MetaData(
                            title = "게시글3",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-03T01:01:01"),
                        ),
                    content = "게시글 내용3",
                ),
                BlogPost(
                    metaData =
                        MetaData(
                            title = "게시글2",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-02T01:01:01"),
                        ),
                    content = "게시글 내용2",
                ),
                BlogPost(
                    metaData =
                        MetaData(
                            title = "게시글1",
                            postUrl = "https://velog.io/@kmkim2689",
                            pubDate = LocalDateTime.parse("2024-06-01T01:01:01"),
                        ),
                    content = "게시글 내용1",
                ),
            ),
        )
    }

    @Test
    fun `주어진 게시물이 최대 개수가 넘어가는 경우 최대 개수 만큼만 가져온다`() {
        // given
        val posts = blogPostFixtures(11)

        // when
        val blogPosts = BlogPosts(posts)

        // then
        assertThat(blogPosts.contents(10)).hasSize(10)
    }

    private fun blogPostFixtures(count: Int): List<BlogPost> {
        return List(count) { index ->
            BlogPost(
                MetaData(
                    "게시글${index + 1}",
                    "https://velog.io/@kmkim2689",
                    LocalDateTime.of(2024, 6, 1, 1, 1, 1).plusDays(index.toLong()),
                ),
                content = "게시글 내용${index + 1}",
            )
        }
    }
}