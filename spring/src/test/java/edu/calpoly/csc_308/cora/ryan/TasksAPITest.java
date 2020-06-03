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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.ResponseEntity;

import org.mockito.Mockito;

import edu.calpoly.csc_308.cora.api.TasksAPI;
import edu.calpoly.csc_308.cora.api.request.VolunteerFilterRequestModel;
import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse;
import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse.VolunteerTaskResponse;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskDAO;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskRepository;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskDAO.TaskProfile;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TasksAPITest {

    @LocalServerPort
    private int port;
    
    @Autowired
    private TasksAPI api;

    @Autowired
    VolunteerTaskRepository repo;

    @Test
    void contextLoads() throws Exception {
        assertThat(api).isNotNull();
    }


    @Test
    void testGetTasks(){
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

        VolunteerFilterRequestModel request1 = new VolunteerFilterRequestModel([], [], -1, "", "");
        VolunteerFilterRequestModel request2 = new VolunteerFilterRequestModel([], [], -1, "Long Beach, CA", "");
        VolunteerFilterRequestModel request3 = new VolunteerFilterRequestModel([], [], 1, "", "");
        VolunteerFilterRequestModel request3 = new VolunteerFilterRequestModel([], [], -1, "", "Supply");
        

        assertEquals(new VolunteerTasksResponse(taskList), api.getVolunteerTasks(request1));
        assertEquals(new VolunteerTasksResponse(taskList), api.getVolunteerTasks(request2));

        taskList.remove(1);
        assertEquals(new VolunteerTasksResponse(taskList), api.getVolunteerTasks(request3));
        assertEquals(new VolunteerTasksResponse(taskList), api.getVolunteerTasks(request4));

        
    }

    @Test
    void testGetSingleTask() {
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
                )
            ));

        assertEquals(ResponseEntity.ok().body(taskList.get(0)), api.getSingleVolunteerTask(0));
    }
}