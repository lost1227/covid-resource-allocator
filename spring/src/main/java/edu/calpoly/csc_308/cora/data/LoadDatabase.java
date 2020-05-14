package edu.calpoly.csc_308.cora.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(VolunteerTaskRepository repository) {
        return args -> {
            logger.info("Preloading " + repository.save(
                new VolunteerTask(
                    "Supply Collector",
                     "Long Beach, CA",
                     1,
                     "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
                     "Memorialcare Health System"
                )));
                logger.info("Preloading " + repository.save(
                new VolunteerTask(
                    "Pamphlet Designer",
                    "Long Beach, CA",
                    0,
                    "A graphic designer is needed to assist in the creation of informational brocures and pamphlets that will help inform the community on how to stay safe during the COVID pandemic.",
                    "Blue Shield of California"
                )));
        };
    }
    Logger logger1 = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initSupplyDatabase(SupplyRepository supplyrepository) {
        return args -> {
            logger1.info("Preloading " + supplyrepository.save(
                new Supply(
                    "KN-95 Masks",
                    "Tempe, AZ",
                    1,
                    "In need of extra KN-95 medical grade masks.",
                    "Local Clinic ACME"
                )));
        };
    }
}
