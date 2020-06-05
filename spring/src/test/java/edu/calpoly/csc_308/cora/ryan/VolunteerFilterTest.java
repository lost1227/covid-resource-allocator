package edu.calpoly.csc_308.cora.ryan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import edu.calpoly.csc_308.cora.api.request.VolunteerFilterRequestModel;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskDAO;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskRepository;

@AutoConfigureTestDatabase
@SpringBootTest
class VolunteerFilterTest {

    @Autowired
    VolunteerTaskRepository repo;

    @BeforeEach
    void setup() {
      repo.deleteAll();
    }

    @Test
    void contextLoads() throws Exception {
        assertThat(repo).isNotNull();
    }

    @Test
    void testTasksByFilter(){
        assertThat(repo.findAll(), is(empty()));

        List<VolunteerTaskDAO> taskList = new ArrayList<>();
                                            
        taskList.add(repo.save(
            new VolunteerTaskDAO(
                new VolunteerTaskDAO.TaskProfile("Supply Collector",
                    "Long Beach, CA",
                    "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
                    "No instructions."
                    ),
                1,
                3L,
                "",
                -1L
                )));
        
        taskList.add(repo.save(
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
                )));

        assertThat(repo.findAll(), contains(taskList.toArray()));

        VolunteerFilterRequestModel request1 = new VolunteerFilterRequestModel();

        VolunteerFilterRequestModel request2 = new VolunteerFilterRequestModel();
        request2.setEnabledFilters(new String[]{"location"});
        request2.setLocation("Long Beach, CA");

        VolunteerFilterRequestModel request3 = new VolunteerFilterRequestModel();
        request3.setEnabledFilters(new String[]{"need"});
        request3.setNeed(1);

        VolunteerFilterRequestModel request4 = new VolunteerFilterRequestModel();
        request4.setEnabledFilters(new String[]{"search"});
        request4.setSearch("Supply");

        assertThat(repo.tasksByFilters(request1), contains(taskList.toArray()));
        assertThat(repo.tasksByFilters(request2), contains(taskList.toArray()));

        taskList.remove(1);
        assertThat(repo.tasksByFilters(request3), contains(taskList.toArray()));
        assertThat(repo.tasksByFilters(request4), contains(taskList.toArray()));

        
    }
}
