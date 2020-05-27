package edu.calpoly.csc_308.cora.api.response;

import java.util.List;

public class FindUsersResponse extends ResponseModel {

    public String query;

    public List<UserInfoResponse> users;

    public FindUsersResponse(String query, List<UserInfoResponse> users) {
        this.query = query;
        this.users = users;
    }
    
}
