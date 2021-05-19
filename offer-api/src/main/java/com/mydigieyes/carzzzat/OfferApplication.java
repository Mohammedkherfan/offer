package com.mydigieyes.carzzzat;

import com.mydigieyes.carzzzat.correlation.EnableCorrelationFilter;
import com.mydigieyes.carzzzat.monitor.EnableMonitoring;
import com.mydigieyes.carzzzat.persistence.EnableAuditing;
import com.mydigieyes.carzzzat.swagger.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger
@EnableAuditing
@SpringBootApplication
@EnableMonitoring(service = "OFFER")
@EnableCorrelationFilter(applicationPrefix = "OFFER")
public class OfferApplication {

    public static void main(String str[]) {
        SpringApplication.run(OfferApplication.class, str);
    }
}
