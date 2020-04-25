package edu.calpoly.csc_308.cora;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.calpoly.csc_308.cora.data.VolunteerTask;
import edu.calpoly.csc_308.cora.data.VolunteerTaskRepository;

@RestController
public class CoraApiController {

    private final VolunteerTaskRepository repo;

    CoraApiController(VolunteerTaskRepository repo) {
        this.repo = repo;
    }
    
    @GetMapping("/api/tasks")
    public List<VolunteerTask> getVolunteerTasks() {
        return repo.findAll();
    }

}
