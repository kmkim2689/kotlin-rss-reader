package rss.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rss.data.model.BlogPostResponse
import rss.data.model.BlogResponse
import rss.data.model.Image
import javax.xml.parsers.DocumentBuilderFactory

class DefaultBlogRemoteDataSource : BlogRemoteDataSource {
    private val factory = DocumentBuilderFactory.newInstance()

    override suspend fun postsByUrl(url: String): BlogResponse = withContext(Dispatchers.IO) {

        val xml = factory.newDocumentBuilder().parse(url)
        val channels = xml.getElementsByTagName("channel")

        val channelNode = channels.item(0)
        val channelDocument = channelNode.ownerDocument
        val title = channelDocument.getElementsByTagName("title").item(0).firstChild.textContent
        val link = channelDocument.getElementsByTagName("link").item(0).firstChild.textContent
        val description = channelDocument.getElementsByTagName("description").item(0).firstChild.textContent
        val lastBuildDate = channelDocument.getElementsByTagName("lastBuildDate").item(0).firstChild.textContent

        val image = channelDocument.getElementsByTagName("image")

        val imageDocument = image.item(0).ownerDocument
        val imageUrl = imageDocument.getElementsByTagName("url").item(0).firstChild.textContent
        val imageTitle = imageDocument.getElementsByTagName("title").item(0).firstChild.textContent
        val imageLink = imageDocument.getElementsByTagName("link").item(0).firstChild.textContent

        val imageResponse = Image(imageTitle, imageUrl, imageLink)

        val itemTags = channelDocument.getElementsByTagName("item")

        val items = (0..<itemTags.length).map {
            val itemDocument = itemTags.item(it).ownerDocument
            val itemTitle = itemDocument.getElementsByTagName("title").item(0).firstChild.textContent
            val itemLink = itemDocument.getElementsByTagName("link").item(0).firstChild.textContent
            val itemPubDate = itemDocument.getElementsByTagName("pubDate").item(0).firstChild.textContent
            val itemDescription = itemDocument.getElementsByTagName("description").item(0).firstChild.textContent
            BlogPostResponse(itemTitle, itemLink, itemPubDate, itemDescription)
        }

        BlogResponse(
            title = title,
            link = link,
            description = description,
            lastBuildDate = lastBuildDate,
            image = imageResponse,
            items = items
        )
    }
}
