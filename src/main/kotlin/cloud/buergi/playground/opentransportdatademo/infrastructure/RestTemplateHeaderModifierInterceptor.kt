package cloud.buergi.playground.opentransportdatademo.infrastructure

import org.springframework.http.HttpRequest
import org.springframework.http.MediaType
import org.springframework.http.client.*
import org.springframework.util.MimeType

class RestTemplateHeaderModifierInterceptor
    : ClientHttpRequestInterceptor {
    override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        var response = execution.execute(request, body)
        var contentType = response.getHeaders().contentType
        val hasToBeReplaced = contentType!!.equals(MediaType.TEXT_HTML) && request.uri.path.contains("gtfs")
        if (hasToBeReplaced) {
            response.headers.contentType = MediaType.asMediaType(MimeType("application","x-protobuf"))
        }
        return response
    }
}