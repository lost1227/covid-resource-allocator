import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
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
      filters = new VolunteerTasksFilter([], [], 0, null, null);
    }
    return super.verifyResponse(this.http.post<VolunteerTasksResponse>("/api/tasks", filters));
  }

  getTask(id : number) : Observable<VolunteerTaskResponse> {
    let params = new HttpParams();
    params = params.append("id", String(id))
    return super.verifyResponse(this.http.get<VolunteerTaskResponse>("/api/task", {params: params}))
  }

  postNewTask(task : PostVolunteerTaskRequest) : Observable<VolunteerTaskResponse> {
    return super.verifyResponse(this.http.post<VolunteerTaskResponse>("/api/tasks/post", task));
  } 
}

export class VolunteerTasksFilter {
  constructor(
    public enabledFilters : string[],
    public skillSet : string[],
    public need : number,
    public location : string,
    public search : string
  ) {}
}

export class PostVolunteerTaskRequest {
  constructor(
    public name : string,
    public location : string,
    public need : number,
    public description : string,
    public instructions : string,
    public ownerId : number,
    public skillNeeded : string,
    public photoId : number
  ) {}
}

export interface VolunteerTaskResponse extends ResponseModel {
  id : number
  name : string
  location : string
  need : number
  description : string
  instructions : string
  ownerId : number
  skillNeeded : string
  photoId : number
}
export interface VolunteerTasksResponse extends ResponseModel {
  tasks : VolunteerTaskResponse[]
}

