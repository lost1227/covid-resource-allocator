package edu.calpoly.csc_308.cora;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.calpoly.csc_308.cora.data.VolunteerTask;
import edu.calpoly.csc_308.cora.data.VolunteerTaskRepository;

@RestController
public class CoraApiController {

    public static class VolunteerFiltersRequestModel {
        public Boolean matchSkillset;
        public Boolean highNeed;
        public Integer locationDistance;
    } 

    private final VolunteerTaskRepository repo;

    CoraApiController(VolunteerTaskRepository repo) {
        this.repo = repo;
    }
    
    @PostMapping("/api/tasks")
    public List<VolunteerTask> getVolunteerTasks(@RequestBody VolunteerFiltersRequestModel request ) {
        return repo.findAll();
    }

}
