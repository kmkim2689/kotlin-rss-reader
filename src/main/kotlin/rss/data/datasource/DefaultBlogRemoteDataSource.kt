package rss.data.datasource

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import org.w3c.dom.Element
import rss.data.model.BlogPostResponse
import rss.data.model.BlogResponse
import rss.data.model.ImageResponse
import javax.xml.parsers.DocumentBuilderFactory

class DefaultBlogRemoteDataSource : BlogRemoteDataSource {
    private val factory = DocumentBuilderFactory.newInstance()

    // TODO 함수 분리하기
    override suspend fun postsByUrl(url: String): BlogResponse {
        val xml =
            withContext(Dispatchers.IO) {
                factory.newDocumentBuilder().parse(url)
            }
        val channels = xml.getElementsByTagName("channel")

        val channelNode = channels.item(0) as Element
        val title = channelNode.parseTextContent("title", 0)
        val link = channelNode.parseTextContent("link", 0)
        val description = channelNode.parseTextContent("description", 0)
        val lastBuildDate = channelNode.parseTextContent("lastBuildDate", 0)

        val imageResponse =
            (channelNode.getElementsByTagName("image").item(0) as? Element)?.let {
                val imageUrl = it.parseTextContent("url", 0)
                val imageTitle = it.parseTextContent("title", 0)
                val imageLink = it.parseTextContent("link", 0)
                ImageResponse(imageTitle.await(), imageUrl.await(), imageLink.await())
            }

        val itemTags = channelNode.getElementsByTagName("item")

        val items =
            (0..<itemTags.length).map { index ->
                withContext(Dispatchers.Default) {
                    async {
                        val itemElement = itemTags.item(index) as Element
                        val itemTitle = itemElement.parseTextContent("title", 0)
                        val itemLink = itemElement.parseTextContent("link", 0)
                        val itemPubDate = itemElement.parseTextContent("pubDate", 0)
                        val itemDescription = itemElement.parseTextContent("description", 0)

                        BlogPostResponse(
                            itemTitle.await(),
                            itemLink.await(),
                            itemPubDate.await(),
                            itemDescription.await(),
                        )
                    }
                }
            }

        return BlogResponse(
            title = title.await(),
            link = link.await(),
            description = description.await(),
            lastBuildDate = lastBuildDate.await(),
            imageResponse = imageResponse,
            items = items.awaitAll(),
        )
    }

    private suspend fun Element.parseTextContent(
        tagName: String,
        index: Int = 0,
    ): Deferred<String> {
        return withContext(Dispatchers.Default) {
            async {
                val child =
                    this@parseTextContent
                        .getElementsByTagName(tagName)
                        .item(index)
                        .firstChild
                child?.textContent ?: "없음" // TODO data단에서 할 일은 아님...
            }
        }
    }
}
