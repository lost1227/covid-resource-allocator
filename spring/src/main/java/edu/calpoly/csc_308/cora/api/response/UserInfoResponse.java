package edu.calpoly.csc_308.cora.api.response;

public class UserInfoResponse extends ResponseModel {
    public Long id;
    public String name;
    public String location;
    public String userType;
    public String description;
    public String skillset;

    public UserInfoResponse(Long id, String name, String location, String userType, String description, String skillset) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.userType = userType;
        this.description = description;
        this.skillset = skillset;
    }
}
