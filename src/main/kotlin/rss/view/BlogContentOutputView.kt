package rss.view

import rss.domain.collection.Blog

object BlogContentOutputView {
    fun logContents(blogs: List<Blog>) {
        blogs.forEach {
            println("=================================================================================================")
            println("블로그명 : ${it.title}")
            println("블로그 링크 : ${it.link}")
            println("블로그 설명 : ${it.description}")
            println("최신 업데이트 : ${it.lastBuildDate}")
            println("-------------------------------------------------------------------------------------------------")
            val posts = it.posts.contents()
            posts.forEachIndexed { index, post ->
                println("#${index + 1}")
                println("게시글 제목 : ${post.metaData.title}")
                println("게시글 링크 : ${post.metaData.postUrl}")
                println("게시일 : ${post.metaData.pubDate}")
                println("미리보기 : ${post.content.split("\n").firstOrNull()}")
                if (index != posts.size - 1) println()
            }
            println("=================================================================================================")
        }
    }
}
