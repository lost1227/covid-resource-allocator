package edu.calpoly.csc_308.cora.ryan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import edu.calpoly.csc_308.cora.api.TasksAPI;
import edu.calpoly.csc_308.cora.api.request.PostVolunteerTaskRequestModel;
import edu.calpoly.csc_308.cora.api.request.VolunteerFilterRequestModel;
import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse;
import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse.VolunteerTaskResponse;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskDAO;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class VolunteerFilterTest {

    @LocalServerPort
    private int port;
    
    @Autowired
    private TasksAPI api;

    @Autowired
    VolunteerTaskRepository repo;

    @BeforeEach
    void setup() {
      repo.deleteAll();
    }

    @Test
    void contextLoads() throws Exception {
        assertThat(api).isNotNull();
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

        /*VolunteerFilterRequestModel request1 = new VolunteerFilterRequestModel([], [], -1, "", "");
        VolunteerFilterRequestModel request2 = new VolunteerFilterRequestModel([], [], -1, "Long Beach, CA", "");
        VolunteerFilterRequestModel request3 = new VolunteerFilterRequestModel([], [], 1, "", "");
        VolunteerFilterRequestModel request4 = new VolunteerFilterRequestModel([], [], -1, "", "Supply");
        */

        VolunteerFilterRequestModel request1 = new VolunteerFilterRequestModel();

        VolunteerFilterRequestModel request2 = new VolunteerFilterRequestModel();
        request2.setLocation("Long Beach, CA");

        VolunteerFilterRequestModel request3 = new VolunteerFilterRequestModel();
        request3.setNeed(1);

        VolunteerFilterRequestModel request4 = new VolunteerFilterRequestModel();
        request4.setSearch("Supply");

        assertEquals(taskList, repo.tasksByFilters(request1));
        assertEquals(taskList, repo.tasksByFilters(request2));

        taskList.remove(1);
        assertEquals(taskList, repo.tasksByFilters(request3));
        assertEquals(taskList, repo.tasksByFilters(request4));

        
    }

    @Test
    void testPostVolunteerTask() {
        assertThat(repo.findAll(), is(empty()));

        List<VolunteerTaskDAO> taskList = new ArrayList<>();
        
        taskList.add(new VolunteerTaskDAO(
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
        
        PostVolunteerTaskRequestModel request1 = new PostVolunteerTaskRequestModel();
        request1.setName("Pamphlet Designer");
        request1.setLocation("Long Beach, CA");
        request1.setNeed(0);
        request1.setDescription("A graphic designer is needed to assist in the creation of informational brocures and pamphlets that will help inform the community on how to stay safe during the COVID pandemic.");
        request1.setInstructions("Message me for more instructions.");
        request1.setOwnerId(4L);
        request1.setSkillNeeded("Graphic Design");
        request1.setPhotoId(-1L);
        
        api.postVolunteerTask(request1);

        VolunteerFilterRequestModel filter1 = new VolunteerFilterRequestModel();

        List<VolunteerTaskResponse> tasks = taskList.stream().map( dao ->
          new VolunteerTaskResponse(dao.getId(), dao.getName(), 
                    dao.getLocation(), dao.getNeed(), dao.getDescription(), 
                    dao.getInstructions(), dao.getOwnerId(), dao.getSkillNeeded(), 
                    dao.getPhotoId())
                    ).collect(Collectors.toList());
        
        assertEquals(new VolunteerTasksResponse(tasks), api.getVolunteerTasks(filter1));
    }
}