package rss.domain.reader

import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import rss.data.repository.FakeBlogsRepository

class DefaultRssReaderTest {
    @Test
    fun `RSS 리더의 블로그 url을 추가할 수 있다`() =
        runTest {
            // given
            val blogsRepository = FakeBlogsRepository(this)
            val rssReader = DefaultRssReader(blogsRepository)
            val urls =
                listOf(
                    "https://techblog.woowahan.com/feed/",
                    "https://velog.io/@kmkim2689",
                )

            // when
            val actual = rssReader.addUrl(*urls.toTypedArray())

            // then
            assertThat(actual.blogUrls).isEqualTo(
                listOf(
                    "https://techblog.woowahan.com/feed/",
                    "https://velog.io/@kmkim2689",
                ),
            )
        }

//    @Test
//    fun `블로그 별 게시글들을 업데이트할 수 있다`() = runTest {
//        // given
//        val blogsRepository = FakeBlogsRepository(this)
//        val rssReader = DefaultRssReader(
//            blogsRepository,
//            listOf(
//                "https://techblog.woowahan.com/feed/",
//                "https://velog.io/@kmkim2689"
//            )
//        )
//
//        // when
//        val blogs = rssReader.updatedBlogs(10, Sort.LATEST)
//
//        // then
// //        assertThat(blogs).isEqualTo(null)
//        assertAll(
//            { assertThat(blogs.map { it.lastBuildDate }) }
//        )
//    }
}
