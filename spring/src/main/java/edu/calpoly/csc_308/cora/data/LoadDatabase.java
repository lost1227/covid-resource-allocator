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
    CommandLineRunner initDatabase(TaskRepository repository) {
        return args -> {
            logger.info("Preloading " + repository.save(
                new Task(
                    "Supply Collector",
                     "Long Beach, CA",
                     "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities."
                )));
                logger.info("Preloading " + repository.save(
                new Task(
                    "Pamphlet Designer",
                        "Long Beach, CA",
                        "A graphic designer is needed to assist in the creation of informational brocures and pamphlets that will help inform the community on how to stay safe during the COVID pandemic."
                )));
        };
    }
}
