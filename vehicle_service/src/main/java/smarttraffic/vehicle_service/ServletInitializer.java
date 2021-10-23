package smarttraffic.vehicle_service;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(VehicleServiceApplication.class);
    }

}
// du inch anes es branchum kanes...
// bayc misht gorct skseluc araj mi hat pull es anum kam, update... serveric tarm fayler@ qashum es
// heto nor tarmi vra gorc es anum u  push es anum... heotok vor du push anes, es merge kanem..
// odkranic heto sax arden nor tarm fayler@ kqashen... uok tenc sharsdc
// hima vor du hesa push anes.. originum el , remotum el es nuyn anunov branch ksarqviok