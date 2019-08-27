package cloud.buergi.playground.opentransportdatademo.service

import com.google.transit.realtime.GtfsRealtime
import com.googlecode.protobuf.format.JsonFormat
import org.springframework.http.*
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import com.google.transit.realtime.GtfsRealtime.FeedEntity
import com.google.transit.realtime.GtfsRealtime.FeedMessage
import org.springframework.beans.factory.annotation.Value


@Service
class GtfsTransportDataService {

    @Value("\${opentransportdata-demo.api-key}")
    lateinit var apiKey: String

    @Value("\${opentransportdata-demo.realtime-url}")
    lateinit var realtimeUrl: String

    fun fetchTransportData() {
        convertProtobufMessageStreamToJsonString()
    }


    @Throws(IOException::class)
    private fun convertProtobufMessageStreamToJsonString() {
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