package edu.calpoly.csc_308.cora.data.supplies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.calpoly.csc_308.cora.entities.Supply.SupplyType;

@Configuration
public class SupplyConfig {
    Logger logger = LoggerFactory.getLogger(SupplyConfig.class);
    @Bean
    CommandLineRunner initSupplyDatabase(SupplyRepository supplyrepository) {
      return args -> {
        SupplyDAO supply = supplyrepository.save(
          new SupplyDAO(
            "KN-95 Masks",
            "Tempe, AZ",
            1,
            "In need of extra KN-95 medical grade masks.",
            5L,
            SupplyType.REQUEST,
            0,
            -1L
        ));
        logger.info("Preloading {}", supply);
      };
    }
}
