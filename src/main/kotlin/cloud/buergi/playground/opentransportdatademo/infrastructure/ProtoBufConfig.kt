package cloud.buergi.playground.opentransportdatademo.infrastructure

import com.google.transit.realtime.GtfsRealtime
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.util.Arrays
import java.util.Collections


@Configuration
class ProtoBufConfig {
    @Bean
    fun protobufHttpMessageConverter(): ProtobufHttpMessageConverter {
        var protobufHttpMessageConverter = ProtobufHttpMessageConverter()
        var supportedMediaTypes = protobufHttpMessageConverter.supportedMediaTypes
        var toMutableList = supportedMediaTypes.toMutableList()
        toMutableList.add(MediaType.TEXT_HTML)
        println(MediaType.TEXT_HTML_VALUE)
        protobufHttpMessageConverter.supportedMediaTypes = toMutableList
        println(protobufHttpMessageConverter.canRead(GtfsRealtime.FeedMessage::class.java, MediaType.TEXT_HTML))
        return protobufHttpMessageConverter
    }

    @Bean
    fun restTemplate(hmc: ProtobufHttpMessageConverter): RestTemplate {
        var restTemplate = RestTemplate(Arrays.asList(hmc) as List<HttpMessageConverter<*>>)
        restTemplate.interceptors.add(RestTemplateHeaderModifierInterceptor())
        return restTemplate
    }
}