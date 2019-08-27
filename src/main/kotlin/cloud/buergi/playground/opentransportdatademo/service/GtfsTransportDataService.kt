package cloud.buergi.playground.opentransportdatademo.service

import com.google.transit.realtime.GtfsRealtime
import com.googlecode.protobuf.format.JsonFormat
import org.springframework.http.*
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.io.InputStream


@Service
class GtfsTransportDataService(val restTemplate: RestTemplate) {
    companion object {
        val headers = HttpHeaders()
        init {
            headers.set("Authorization", "57c5dbbbf1fe4d0001000018b3c53824d57640418b7a694276a16249")
            headers.set("Content-Type","PROTO")
        }
    }

    fun fetchTransportData() {
        var httpEntity = HttpEntity<GtfsRealtime.FeedEntity>(headers)
        restTemplate.exchange("https://api.opentransportdata.swiss/gtfs-rt", HttpMethod.GET, httpEntity, GtfsRealtime.FeedEntity::class.java)
        println(httpEntity.body!!.tripUpdate.delay)
    }


    @Throws(IOException::class)
    private fun convertProtobufMessageStreamToJsonString(protobufStream: InputStream) {
        val jsonFormat = JsonFormat()
        val course = GtfsRealtime.TripUpdate.parseFrom(protobufStream)
        println(jsonFormat.printToString(course))
    }
}