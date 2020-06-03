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
      if(repository.findAll().isEmpty()) {
        VolunteerTaskDAO task1 = repository.save(
          new VolunteerTaskDAO(
            new VolunteerTaskDAO.TaskProfile(
              "Supply Collector",
              "Long Beach, CA",
              "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
              "No instructions."
            ),
            1,
            3L,
            "",
            -1L
        ));
        VolunteerTaskDAO task2 = repository.save(
          new VolunteerTaskDAO(
            new VolunteerTaskDAO.TaskProfile(
              "Pamphlet Designer",
              "Long Beach, CA",
              "A graphic designer is needed to assist in the creation of informational brocures and pamphlets that will help inform the community on how to stay safe during the COVID pandemic.",
              "Message me for more instructions."
            ),
            0,
            4L,
            "Graphic Design",
            -1L
        ));
        logger.info("Preloading {}", task1);
        logger.info("Preloading {}", task2);
      }
    };
  }
}
