package edu.calpoly.csc_308.cora.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import edu.calpoly.csc_308.cora.api.request.PostVolunteerTaskRequestModel;
import edu.calpoly.csc_308.cora.api.request.VolunteerFilterRequestModel;
import edu.calpoly.csc_308.cora.api.response.FailResponse;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskDAO;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskRepository;
import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse;
import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse.VolunteerTaskResponse;
import edu.calpoly.csc_308.cora.api.response.UserInfoResponse;

@RestController
public class TasksAPI {

    @Autowired
    VolunteerTaskRepository repo;
    
    UserInfoResponse user;

    Logger logger = LoggerFactory.getLogger(TasksAPI.class);

    @PostMapping("/api/tasks")
    public ResponseModel getVolunteerTasks(@RequestBody VolunteerFilterRequestModel request) {
      List<VolunteerTaskDAO> daoList;
      if(request.getEnabledFilters().length > 0) {
        daoList = repo.tasksByFilters(request);
      } else {
        daoList = repo.findAll();
      }
      
      List<VolunteerTaskResponse> tasks = daoList.stream().map( dao ->
          new VolunteerTaskResponse(dao.getId(), dao.getName(), dao.getLocation(), dao.getNeed(), dao.getDescription(), dao.getInstructions(), dao.getOwnerId(), dao.getSkillNeeded(), dao.getPhotoId())
      ).collect(Collectors.toList());

      return new VolunteerTasksResponse(tasks);
    }

    @GetMapping("/api/task")
    public ResponseEntity<ResponseModel> getSingleVolunteerTask(@RequestParam Long id) {
      Optional<VolunteerTaskDAO> opDao = repo.findById(id);
      if(!opDao.isPresent()) {
        return ResponseEntity.badRequest().body(new FailResponse());
      }
      VolunteerTaskDAO dao = opDao.get();
      
      VolunteerTaskResponse response = new VolunteerTaskResponse(dao.getId(), dao.getName(), dao.getLocation(), dao.getNeed(), dao.getDescription(), dao.getInstructions(), dao.getOwnerId(), dao.getSkillNeeded(), dao.getPhotoId());
      return ResponseEntity.ok().body(response);
    }

    @PostMapping("/api/tasks/post")
    public ResponseModel postVolunteerTask(@RequestBody PostVolunteerTaskRequestModel request) {
      VolunteerTaskDAO dao = new VolunteerTaskDAO(new VolunteerTaskDAO.TaskProfile(request.getName(), request.getLocation(), request.getDescription(), request.getInstructions()), request.getNeed(), request.getOwnerId(), request.getSkillNeeded(), request.getPhotoId());
      dao = repo.save(dao);
      return new VolunteerTaskResponse(dao.getId(), dao.getName(), dao.getLocation(), dao.getNeed(), dao.getDescription(), dao.getInstructions(), dao.getOwnerId(), dao.getSkillNeeded(), dao.getPhotoId());
    }
    
}
