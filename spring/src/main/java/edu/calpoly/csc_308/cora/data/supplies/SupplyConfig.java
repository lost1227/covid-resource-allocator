package edu.calpoly.csc_308.cora.data.supplies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupplyConfig {
    Logger logger = LoggerFactory.getLogger(SupplyConfig.class);
    @Bean
    CommandLineRunner initSupplyDatabase(SupplyRepository supplyrepository) {
        return args -> {
            logger.info("Preloading " + supplyrepository.save(
                new SupplyDAO(
                    "KN-95 Masks",
                    "Tempe, AZ",
                    1,
                    "In need of extra KN-95 medical grade masks.",
                    "Local Clinic ACME"
                )));
        };
    }
}
