package rss.data.datasource

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import org.w3c.dom.Document
import rss.data.model.BlogPostResponse
import rss.data.model.BlogResponse
import rss.data.model.ImageResponse
import javax.xml.parsers.DocumentBuilderFactory

class DefaultBlogRemoteDataSource : BlogRemoteDataSource {
    private val factory = DocumentBuilderFactory.newInstance()

    // TODO 함수 분리하기
    override suspend fun postsByUrl(url: String): BlogResponse {
        val xml = withContext(Dispatchers.IO) {
            factory.newDocumentBuilder().parse(url)
        }
        val channels = xml.getElementsByTagName("channel")

        val channelNode = channels.item(0)
        val channelDocument = channelNode.ownerDocument
        val title = channelDocument.parseTextContent("title", 0)
        val link = channelDocument.parseTextContent("link", 0)
        val description = channelDocument.parseTextContent("description", 0)
        val lastBuildDate = channelDocument.parseTextContent("lastBuildDate", 0)

        val imageNode = channelDocument.getElementsByTagName("image").item(0)
        val imageDocument = imageNode.ownerDocument
        val imageUrl = imageDocument.parseTextContent("url", 0)
        val imageTitle = imageDocument.parseTextContent("title", 0)
        val imageLink = imageDocument.parseTextContent("link", 0)
        val imageResponse = ImageResponse(imageTitle.await(), imageUrl.await(), imageLink.await())

        val itemTags = channelDocument.getElementsByTagName("item")

        val items = (0..<itemTags.length).map { index ->
            withContext(Dispatchers.Default) {
                async {
                    val itemDocument = itemTags.item(index).ownerDocument
                    val itemTitle = itemDocument.parseTextContent("title", index)
                    val itemLink = itemDocument.parseTextContent("link", index)
                    val itemPubDate = itemDocument.parseTextContent("pubDate", index)
                    val itemDescription = itemDocument.parseTextContent("description", index)
                    BlogPostResponse(itemTitle.await(), itemLink.await(), itemPubDate.await(), itemDescription.await())
                }
            }
        }

        return BlogResponse(
            title = title.await(),
            link = link.await(),
            description = description.await(),
            lastBuildDate = lastBuildDate.await(),
            imageResponse = imageResponse,
            items = items.awaitAll()
        )
    }

    private suspend fun Document.parseTextContent(
        tagName: String,
        index: Int = 0,
    ): Deferred<String> {
        return withContext(Dispatchers.Default) {
            async {
                val child = this@parseTextContent
                    .getElementsByTagName(tagName)
                    .item(index)
                    .firstChild
                child?.textContent ?: "없음" // TODO data단에서 할 일은 아님...
            }
        }
    }
}
