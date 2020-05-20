package edu.calpoly.csc_308.cora.api;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import edu.calpoly.csc_308.cora.api.request.VolunteerFilterRequestModel;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskRepository;
import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse;
import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse.VolunteerTaskResponse;;

@RestController
public class TasksAPI {

    @Autowired
    VolunteerTaskRepository repo;

    Logger logger = LoggerFactory.getLogger(TasksAPI.class);

    @PostMapping("/api/tasks")
    public ResponseModel getVolunteerTasks(@RequestBody VolunteerFilterRequestModel request) {
        // TODO: add filter logic

        List<VolunteerTaskResponse> tasks = repo.findAll().stream().map( dao ->
            new VolunteerTaskResponse(dao.id, dao.name, dao.location, dao.need, dao.description, dao.taskOwnerId)
        ).collect(Collectors.toList());
        
        return new VolunteerTasksResponse(tasks);
    }
    
}