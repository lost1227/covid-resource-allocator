package edu.calpoly.csc_308.cora;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.calpoly.csc_308.cora.data.Request;
import edu.calpoly.csc_308.cora.data.RequestRepository;

@RestController
public class CoraRequestController {

    //Request
    public static class RequestFiltersRequestModel {
        public Boolean matchSkillset;
        public Boolean highNeed;
        public Integer locationDistance;
    }
    
    private final RequestRepository rRepo;

    CoraRequestController(RequestRepository rRepo) {
        this.rRepo = rRepo;
    }

    @PostMapping("/api/tasks")
    public List<Request> getRequests(@RequestBody RequestFiltersRequestModel request ) {
        return rRepo.findAll();
    }

}
