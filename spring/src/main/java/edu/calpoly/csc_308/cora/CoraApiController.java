package edu.calpoly.csc_308.cora;

import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.calpoly.csc_308.cora.data.tasks.VolunteerTask;
import edu.calpoly.csc_308.cora.data.tasks.VolunteerTaskRepository;
import edu.calpoly.csc_308.cora.data.Supply;
import edu.calpoly.csc_308.cora.data.SupplyRepository;
import edu.calpoly.csc_308.cora.entities.User;

@RestController
public class CoraApiController {

    public static class VolunteerFiltersRequestModel {
        public Boolean matchSkillset;
        public Boolean highNeed;
        public Integer locationDistance;
        public String keywords;
    }
    public static class SupplyFiltersRequestModel {
        public Boolean matchSkillset;
        public Boolean highNeed;
        public Integer locationDistance;
    }

    private final VolunteerTaskRepository repo;
    private final SupplyRepository supplyrepo;
    private final User user;

    CoraApiController(VolunteerTaskRepository repo, SupplyRepository supplyrepo, User user) {
        this.repo = repo;
        this.supplyrepo = supplyrepo;
        this.user = user;
    }

    public Boolean evaluateNeed(VolunteerTask task){
        if (task.need > 0)
            return true;
        else
            return false;
    }

    public Boolean evaluateSkills(VolunteerTask task){
        
        for (String skill: task.skillsNeeded){
            for (String volSkill: user.skillSet){
                if (volSkill.equals(skill))
                    continue;
                else
                    return false;
            }
        }

        return true;
    }

    public Boolean matchKeywords(VolunteerTask task, String keywords){
        String[] inputkeywords = keywords.split("[ ,]+");
        String[] titleWords = task.name.split("[ ,]+");
        String[] locWords = task.location.split("[ ,]+");
        ArrayList<String> matchWords = new ArrayList<>();

        for (String s: inputkeywords){
            matchWords.add(s);
        }

        for (String s: locWords){
            matchWords.add(s);
        }

        for (String words: matchWords){
            for (String title: titleWords){
                if (words.equals(title)){
                    continue;
                }
                else
                    return false;
            }
        }
        return true;
    }
    
    @PostMapping("/api/tasks")
    public List<VolunteerTask> getVolunteerTasks(@RequestBody VolunteerFiltersRequestModel request ) {
        List<VolunteerTask> filteredList = new ArrayList<VolunteerTask>();
        
        for(VolunteerTask task: repo.findAll()){
            if (request.highNeed)
                if (evaluateNeed(task) == false)
                    continue;
            else if (request.matchSkillset)
                if (evaluateSkills(task) == false)
                    continue;
            //int locationDiff = user.location - request.location **idk needa figure out how to use GoogleMaps API
            // if (locationDiff > request.locationDistance)
            //        continue;
            
            else if (matchKeywords(task, request.keywords) == false)
                continue;

            filteredList.add(task);
            //when testing, make sure that it skips whatever tasks are false
        }

        return filteredList;
    }

    /*public Boolean addTask(VolunteerTask task){
        if (task != null){
            bound.vol.currentTasks.add(task);
            task.volunteers.add(bound.vol);
            return true;
        }
        else{
            return false;
        }
    }*/

    @PostMapping("/api/supp")
    public List<Supply> getSupplies(@RequestBody SupplyFiltersRequestModel request ) {
        return supplyrepo.findAll();
    }

}
