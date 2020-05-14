package edu.calpoly.csc_308.cora;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.calpoly.csc_308.cora.data.Supply;
import edu.calpoly.csc_308.cora.data.SupplyRepository;
import edu.calpoly.csc_308.cora.data.VolunteerTask;
import edu.calpoly.csc_308.cora.data.VolunteerTaskRepository;

@RestController
public class CoraApiController {

    public static class VolunteerFiltersRequestModel {
        public Boolean matchSkillset;
        public Boolean highNeed;
        public Integer locationDistance;
    }
    public static class SupplyFiltersRequestModel {
        public Boolean matchSkillset;
        public Boolean highNeed;
        public Integer locationDistance;
    }

    private final VolunteerTaskRepository repo;
    private final SupplyRepository supplyrepo;

    CoraApiController(VolunteerTaskRepository repo, SupplyRepository supplyrepo) {
        this.repo = repo;
        this.supplyrepo = supplyrepo;
    }
    
    @PostMapping("/api/tasks")
    public List<VolunteerTask> getVolunteerTasks(@RequestBody VolunteerFiltersRequestModel request ) {
        return repo.findAll();
    }
    @PostMapping("/api/supplies")
    public List<Supply> getSupplies(@RequestBody SupplyFiltersRequestModel request ) {
        return supplyrepo.findAll();
    }

}
