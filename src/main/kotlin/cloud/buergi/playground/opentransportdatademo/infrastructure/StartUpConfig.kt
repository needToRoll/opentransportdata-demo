package cloud.buergi.playground.opentransportdatademo.infrastructure

import cloud.buergi.playground.opentransportdatademo.service.GtfsTransportDataService
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class StartUpConfig(val transportDataService: GtfsTransportDataService) {
    @PostConstruct
    fun startUp() {
        transportDataService.fetchTransportData()
    }

}