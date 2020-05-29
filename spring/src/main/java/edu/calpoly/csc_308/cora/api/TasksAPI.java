package edu.calpoly.csc_308.cora.api;

import java.util.List;
import java.util.ArrayList;
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
import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse.VolunteerTaskResponse;
//import edu.calpoly.csc_308.cora.api.response.VolunteerTasksResponse.VolunteerTaskResponse;
import edu.calpoly.csc_308.cora.api.response.UserInfoResponse;

@RestController
public class TasksAPI {

    @Autowired
    VolunteerTaskRepository repo;
    
    UserInfoResponse user;

    Logger logger = LoggerFactory.getLogger(TasksAPI.class);

    /*public Boolean evaluateSkills(VolunteerTaskResponse task){
        String[] skills = task.skillsNeeded.split("[ ,]+");
        for (String skill: skills){
            for (String volSkill: user.skillSet){
                if (volSkill.equals(skill))
                    continue;
                else
                    return false;
            }
        }

        return true;
    }*/

    public Boolean matchKeywords(VolunteerTaskResponse task, String keywords){
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

    /*public Boolean matchLocation(VolunteerTaskResponse task, UserInfoResponse user){
        String[] inputkeywords = user.location.split("[ ,]+");
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
    }*/

    @PostMapping("/api/tasks")
    public ResponseModel getVolunteerTasks(@RequestBody VolunteerFilterRequestModel request) {
        List<VolunteerTaskResponse> filteredList = new ArrayList<VolunteerTaskResponse>();
        List<VolunteerTaskResponse> tasks = repo.findAll().stream().map( dao ->
            new VolunteerTaskResponse(dao.id, dao.name, dao.location, dao.need, dao.description, dao.taskOwnerId, dao.skillsNeeded)
        ).collect(Collectors.toList());

        for(VolunteerTaskResponse task: tasks){
            if (request.enabledFilters[0].equals("Skillset")){
                if (!request.skillSet.equals(user.skillset))
                    continue;
            }
            else if (request.enabledFilters[1].equals("Priority")){
                if (request.priority < 0)
                    continue;
            }
            else if (request.enabledFilters[2].equals("LocationDistance")){
                if (!request.location.equals(user.location))
                    continue;
            }
            else if (request.enabledFilters[3].equals("Keywords")){
                if (matchKeywords(task, request.keywords) == false)
                    continue;
            }
            filteredList.add(task);
            
        }

        return new VolunteerTasksResponse(filteredList);

        /*List<VolunteerTaskResponse> tasks = repo.findAll().stream().map( dao ->
            new VolunteerTaskResponse(dao.id, dao.name, dao.location, dao.need, dao.description, dao.taskOwnerId)
        ).collect(Collectors.toList());
        
        return new VolunteerTasksResponse(tasks);*/
    }
    
}
