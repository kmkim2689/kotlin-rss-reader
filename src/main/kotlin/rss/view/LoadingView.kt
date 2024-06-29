package rss.view

object LoadingView {
    fun showLoading() {
        println("데이터를 불러오고 있습니다...")
    }

    fun showUpdateLoading() {
        println("새로운 게시글이 있는지 확인 중입니다...\n확인 중에는 키워드를 검색할 수 없습니다.")
    }

    fun showUpdatedNotification() {
        println("구독중인 블로그 게시물이 추가되었습니다.")
    }

    fun showNotUpdatedNotification() {
        println("구독중인 블로그의 게시물이 추가되지 않았습니다.\n")
    }
}
