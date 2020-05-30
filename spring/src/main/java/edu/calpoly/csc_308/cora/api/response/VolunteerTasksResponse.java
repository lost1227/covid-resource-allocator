package edu.calpoly.csc_308.cora.api.response;

import java.util.List;

public class VolunteerTasksResponse extends ResponseModel {
    public static class VolunteerTaskResponse extends ResponseModel {
        public Long id;
    
        public String name;
        public String location;
        
        public Integer need;

        public String description;

        public Long ownerId;

        public String skillNeeded;

        public Long photoId;

        public VolunteerTaskResponse(Long id, String name, String location, Integer need, String description, Long ownerId, String skillNeeded, Long photoId) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.need = need;
            this.description = description;
            this.ownerId = ownerId;
            this.skillNeeded = skillNeeded;
            this.photoId = photoId;
        }
    }

    public List<VolunteerTaskResponse> tasks;

    public VolunteerTasksResponse(List<VolunteerTaskResponse> tasks) {
        this.tasks = tasks;
    }
}
