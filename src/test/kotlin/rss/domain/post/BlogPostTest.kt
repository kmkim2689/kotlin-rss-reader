package rss.domain.post

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import rss.domain.post.BlogPost
import rss.domain.post.MetaData
import java.time.LocalDateTime

class BlogPostTest {
    @ParameterizedTest
    @CsvSource("케이엠,true", "케이엥,false")
    fun `원하는 키워드가 게시물에 존재하는지의 여부를 파악할 수 있다`(
        keyword: String,
        actual: Boolean,
    ) {
        // given
        val blogPost =
            BlogPost(
                metaData =
                    MetaData(
                        title = "케이엠의 동등성과 동일성",
                        postUrl = "https://velog.com",
                        pubDate = LocalDateTime.now(),
                    ),
                content = "케이엠입니다.",
            )

        // when
        val expected = blogPost.contains(keyword)

        // then
        assertThat(expected).isEqualTo(actual)
    }
}
