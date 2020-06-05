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

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import edu.calpoly.csc_308.cora.api.TasksAPI;
import edu.calpoly.csc_308.cora.api.request.PostVolunteerTaskRequestModel;
import edu.calpoly.csc_308.cora.api.request.VolunteerFilterRequestModel;
import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse;
import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse.VolunteerTaskResponse;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskDAO;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskRepository;

@AutoConfigureTestDatabase
@SpringBootTest
class TasksAPITest {

    
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
    void testGetTasks(){
        assertThat(repo.findAll(), is(empty()));

        List<VolunteerTaskDAO> taskList = new ArrayList<>();
                                            
        taskList.add(repo.save(
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

        VolunteerFilterRequestModel request2 = new VolunteerFilterRequestModel();
        request2.setEnabledFilters(new String[]{"location"});
        request2.setLocation("Long Beach, CA");

        VolunteerFilterRequestModel request3 = new VolunteerFilterRequestModel();
        request3.setEnabledFilters(new String[]{"need"});
        request3.setNeed(1);

        VolunteerFilterRequestModel request4 = new VolunteerFilterRequestModel();
        request4.setEnabledFilters(new String[]{"search"});
        request4.setSearch("Supply");
        
        List<VolunteerTaskResponse> tasks = taskList.stream().map( dao -> 
          new VolunteerTaskResponse(dao.getId(), dao.getName(), 
                    dao.getLocation(), dao.getNeed(), dao.getDescription(), 
                    dao.getInstructions(), dao.getOwnerId(), dao.getSkillNeeded(), 
                    dao.getPhotoId())
                    ).collect(Collectors.toList());

        assertThat(((VolunteerTasksResponse) api.getVolunteerTasks(request2)).getTasks(), contains(tasks.toArray()));

        tasks.remove(1);
        assertThat(((VolunteerTasksResponse) api.getVolunteerTasks(request3)).getTasks(), contains(tasks.toArray()));
        assertThat(((VolunteerTasksResponse) api.getVolunteerTasks(request4)).getTasks(), contains(tasks.toArray()));
    }

    @Test
    void testVolunteerTaskEquals() {
      VolunteerTaskDAO dao = repo.save(
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
            );
        VolunteerTaskResponse expected = new VolunteerTaskResponse(dao.getId(), dao.getName(), dao.getLocation(), dao.getNeed(), dao.getDescription(), dao.getInstructions(), dao.getOwnerId(), dao.getSkillNeeded(), dao.getPhotoId());
        VolunteerTaskResponse expected2 = new VolunteerTaskResponse(dao.getId(), dao.getName(), dao.getLocation(), dao.getNeed(), dao.getDescription(), dao.getInstructions(), dao.getOwnerId(), dao.getSkillNeeded(), dao.getPhotoId());
        assertEquals(expected, expected2);
    }

    @Test
    void testGetSingleTask() {
        assertThat(repo.findAll(), is(empty()));

        VolunteerTaskDAO dao = repo.save(
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
            );
        VolunteerTaskResponse expected = new VolunteerTaskResponse(dao.getId(), dao.getName(), dao.getLocation(), dao.getNeed(), dao.getDescription(), dao.getInstructions(), dao.getOwnerId(), dao.getSkillNeeded(), dao.getPhotoId());
        assertEquals(expected, api.getSingleVolunteerTask(dao.getId()).getBody());
    }

    @Test
    void testPostVolunteerTask() {
        assertThat(repo.findAll(), is(empty()));
        
        VolunteerTaskDAO dao  = new VolunteerTaskDAO(
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
                );
        
        PostVolunteerTaskRequestModel request1 = new PostVolunteerTaskRequestModel();
        request1.setName(dao.getName());
        request1.setLocation(dao.getLocation());
        request1.setNeed(dao.getNeed());
        request1.setDescription(dao.getDescription());
        request1.setInstructions(dao.getInstructions());
        request1.setOwnerId(dao.getOwnerId());
        request1.setSkillNeeded(dao.getSkillNeeded());
        request1.setPhotoId(dao.getPhotoId());
        
        VolunteerTaskResponse response = (VolunteerTaskResponse) api.postVolunteerTask(request1);
        dao.setId(response.getId());
        assertThat(repo.findAll(), contains(dao));
    }
}
