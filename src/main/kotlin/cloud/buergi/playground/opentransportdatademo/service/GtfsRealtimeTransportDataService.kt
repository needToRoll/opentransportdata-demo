package cloud.buergi.playground.opentransportdatademo.service

import org.springframework.http.*
import org.springframework.stereotype.Service
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import com.google.transit.realtime.GtfsRealtime.FeedMessage
import org.springframework.beans.factory.annotation.Value


@Service
class GtfsRealtimeTransportDataService {

    @Value("\${opentransportdata-demo.api-key}")
    lateinit var apiKey: String

    @Value("\${opentransportdata-demo.realtime-url}")
    lateinit var realtimeUrl: String

    fun fetchTransportData() {
        convertProtocolBufferMessageStreamToJsonString()
    }

    @Throws(IOException::class)
    private fun convertProtocolBufferMessageStreamToJsonString() {
        val httpURLConnection = URL(realtimeUrl).openConnection() as HttpURLConnection
        httpURLConnection.addRequestProperty(HttpHeaders.AUTHORIZATION, apiKey)
        val feed = FeedMessage.parseFrom(httpURLConnection.inputStream)
        for (entity in feed.entityList) {
            if (entity.hasTripUpdate()) {
                println(entity.tripUpdate)
            }
        }
    }
}