package cloud.buergi.playground.opentransportdatademo.infrastructure

import cloud.buergi.playground.opentransportdatademo.service.GtfsTransportDataService
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class StartUpConfig(val gtfsTransportDataService: GtfsTransportDataService) {
    @PostConstruct
    fun startUp() {
        gtfsTransportDataService.fetchTransportData()
    }

}