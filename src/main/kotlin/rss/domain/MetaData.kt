package rss.domain

data class MetaData(
    val title: String,
    val url: String,
) {
    init {
        require(title.trim().isNotEmpty()) { EXCEPTION_EMPTY_TITLE }
        require(url.trim().contains(VALID_PROTOCOL)) { EXCEPTION_INVALID_URL_PROTOCOL }
    }

    companion object {
        private const val VALID_PROTOCOL = "https://"
        private const val EXCEPTION_EMPTY_TITLE = "게시글 제목은 1자 이상이어야 합니다."
        private const val EXCEPTION_INVALID_URL_PROTOCOL = "유효한 프로토콜의 주소가 아닙니다."
    }
}
