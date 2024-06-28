package rss.view

object KeywordSearchInputView {
    fun keyword(): String {
        println("검색할 키워드를 입력하세요.")
        return readlnOrNull() ?: throw IllegalArgumentException()
    }
}
