import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

import { Observable} from 'rxjs';
import { SuppliesListComponent } from './supplies/supplies-list/supplies-list.component';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http : HttpClient) { }

  getVolunteerTasks(filters : VolunteerFilter) : Observable<VolunteerTask[]> {
    return this.http.post<VolunteerTask[]>("/api/tasks", filters)
  }
  getSuppliesList(filters : SupplyFilter) : Observable<Supply[]> {
    return this.http.post<Supply[]>("/api/tasks", filters)
  }
}

export interface VolunteerTask {
  id : number
  name : string
  location : string
  need : number
  description : string
  taskOwner : string
}

export class VolunteerFilter {
  constructor(
    public matchSkillset : boolean,
    public highNeed : boolean,
    public locationDistance : number
  ) {}
}
export interface Supply {
  id : number
  name : string
  location : string
  need : number
  description : string
  taskOwner : string
}

export class SupplyFilter {
  constructor(
    public matchSkillset : boolean,
    public highNeed : boolean,
    public locationDistance : number
  ) {}
}