package edu.calpoly.csc_308.cora.data;

import java.util.List;
import java.util.ArrayList;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class ViewRequestsController {
    public Boolean matchSkillset;
    public Boolean highNeed;
    public Integer locationDistance;
    public ViewRequestBoundary bound;
    private final VolunteerTaskRepository repo;
    public String keywords;

    public ViewRequestsController(VolunteerTaskRepository repo, ViewRequestBoundary bound){
        this.repo = repo;
        this.bound = bound;
        this.keywords = ViewRequestBoundary.enterKeyWords();
    }
    
    public Boolean evaluateNeed(VolunteerTask task){
        if (task.need > 0)
            return true;
        else
            return false;
    }

    public Boolean evaluateSkills(VolunteerTask task){
        
        for (String skill: task.skillsNeeded){
            for (String volSkill: bound.vol.skillset){ //volunteer class must have skillset field
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

    public List<VolunteerTask> getVolunteerTasks(ViewRequestsController request) {
        List<VolunteerTask> filteredList = new ArrayList<VolunteerTask>();
        
        for(VolunteerTask task: repo.findAll()){
            if (highNeed)
                if (evaluateNeed(task) == false)
                    continue;
            else if (matchSkillset)
                if (evaluateSkills(task) == false)
                    continue;
            //int locationDiff = vol.location - task.location **idk needa figure out how to use GoogleMaps API
            // if (locationDiff > this.locationDistance)
            //        continue;
            
            else if (matchKeywords(task, keywords) == false)
                continue;

            filteredList.add(task);
            //when testing, make sure that it skips whatever tasks are false
        }

        return filteredList;
    }

    public Boolean addTask(VolunteerTask task){
        if (task != null){
            bound.vol.currentTasks.add(task);
            task.volunteers.add(bound.vol);
            return true;
        }
        else{
            return false;
        }
    }
}