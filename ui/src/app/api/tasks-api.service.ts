import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiService, ResponseModel } from '@app/api/api.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TasksApiService extends ApiService {

  constructor(
    private http : HttpClient
  ) {
    super();
  }

  getTasks(filters : VolunteerTasksFilter) : Observable<VolunteerTasksResponse> {
    if(filters == null) {
      filters = new VolunteerTasksFilter([], [], 0, 0);
    }
    return super.verifyResponse(this.http.post<VolunteerTasksResponse>("/api/tasks", filters));
  }
}

export class VolunteerTasksFilter {
  constructor(
    public enabledFilters : string[],
    public skillSet : string[],
    public priority : number,
    public locationDistance : number
  ) {}
}

export interface VolunteerTaskResponse {
  id : number
  name : string
  location : string
  need : number
  description : string
  taskOwnerId : number
}
export interface VolunteerTasksResponse extends ResponseModel {
  tasks : VolunteerTaskResponse[]
}

