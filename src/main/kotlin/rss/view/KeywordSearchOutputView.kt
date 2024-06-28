package rss.view

import rss.domain.post.Post

object KeywordSearchOutputView {
    fun logContentsByKeyword(
        keyword: String,
        posts: List<Post>,
    ) {
        println("\n<$keyword> 검색 결과 : ${posts.size}건")
        println("=================================================================================================")
        posts.forEachIndexed { index, post ->
            println("검색 결과 #${index + 1}")
            println("게시글 제목 : ${post.metaData.title}")
            println("게시글 링크 : ${post.metaData.postUrl}")
            println("게시일 : ${post.metaData.pubDate}")
            println("미리보기 : ${post.content.split("\n").firstOrNull()}")
            if (index != posts.size - 1) println()
        }
        println("=================================================================================================")
    }
}
