package cloud.buergi.playground.opentransportdatademo.infrastructure

import cloud.buergi.playground.opentransportdatademo.service.GtfsRealtimeTransportDataService
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class StartUpConfig(val transportDataService: GtfsRealtimeTransportDataService) {
    @PostConstruct
    fun startUp() {
        transportDataService.fetchTransportData()
    }

}