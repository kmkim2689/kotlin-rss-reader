package rss.domain.post

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime

class MetaDataTest {
    @ParameterizedTest
    @ValueSource(strings = ["", " "])
    fun `게시글 제목이 존재하지 않으면 예외를 발생시킨다`(title: String) {
        assertThrows<IllegalArgumentException> {
            MetaData(title, "https://velog.com", LocalDateTime.now())
        }
    }

    @Test
    fun `게시글 주소에 프로토콜이 존재하지 않으면 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            MetaData("hello", "velog.com", LocalDateTime.now())
        }
    }
}
