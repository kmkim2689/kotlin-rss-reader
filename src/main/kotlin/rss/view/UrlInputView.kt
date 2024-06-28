package rss.view

object UrlInputView {
    fun urls(): List<String> {
        val number = numberOfUrls()
        println(MESSAGE_INPUT_URLS)
        return List(number) {
            url()
        }
    }

    private fun url() = readlnOrNull() ?: throw IllegalArgumentException()

    private fun numberOfUrls(): Int {
        println(MESSAGE_INPUT_URL_NUMBER)
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException()
    }


    private const val MESSAGE_INPUT_URL_NUMBER = "열람하고 싶은 블로그 주소들의 수를 입력하세요."
    private const val MESSAGE_INPUT_URLS = "열람하고 싶은 블로그 주소들을 한 줄에 하나씩 입력하세요."
}
