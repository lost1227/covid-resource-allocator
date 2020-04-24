package edu.calpoly.csc_308.cora;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.calpoly.csc_308.cora.data.Task;
import edu.calpoly.csc_308.cora.data.TaskRepository;

@RestController
public class CoraApiController {

    private final TaskRepository repo;

    CoraApiController(TaskRepository repo) {
        this.repo = repo;
    }
    
    @GetMapping("/api/tasks")
    public List<Task> getTasks() {
        return repo.findAll();
    }

}
