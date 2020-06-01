package edu.calpoly.csc_308.cora.api.response;

import java.util.List;
import java.util.Collections;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class FindUsersResponse extends ResponseModel {

    private final String query;

    private final List<UserInfoResponse> users;

    public FindUsersResponse(String query, List<UserInfoResponse> users) {
        this.query = query;
        this.users = Collections.unmodifiableList(users);
    }
    
}
