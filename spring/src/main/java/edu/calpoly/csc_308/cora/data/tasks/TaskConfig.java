package edu.calpoly.csc_308.cora.data.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    Logger logger = LoggerFactory.getLogger(TaskConfig.class);

    @Bean
    CommandLineRunner initTasks(VolunteerTaskRepository repository) {
        return args -> {
            logger.info("Preloading " + repository.save(
                new VolunteerTaskDAO(
                    "Supply Collector",
                     "Long Beach, CA",
                     1,
                     "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
                     3L
                )));
                logger.info("Preloading " + repository.save(
                new VolunteerTaskDAO(
                    "Pamphlet Designer",
                    "Long Beach, CA",
                    0,
                    "A graphic designer is needed to assist in the creation of informational brocures and pamphlets that will help inform the community on how to stay safe during the COVID pandemic.",
                    4L
                )));
        };
    }
}
