package edu.calpoly.csc_308.cora.api.response;

import java.util.List;

public class VolunteerTasksResponse extends ResponseModel {
    public static class VolunteerTaskResponse {
        public Long id;
    
        public String name;
        public String location;
        
        public Integer need;

        public String description;

        public Long taskOwnerId;

        public String skillNeeded;

        public VolunteerTaskResponse(Long id, String name, String location, Integer need, String description, Long taskOwnerId, String skillNeeded) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.need = need;
            this.description = description;
            this.taskOwnerId = taskOwnerId;
            this.skillNeeded = skillNeeded;

        }
    }

    public List<VolunteerTaskResponse> tasks;

    public VolunteerTasksResponse(List<VolunteerTaskResponse> tasks) {
        this.tasks = tasks;
    }
}
