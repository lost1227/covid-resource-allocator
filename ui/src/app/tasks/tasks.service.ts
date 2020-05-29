import { Injectable } from '@angular/core';
import { TasksApiService, VolunteerTasksFilter } from '@app/api/tasks-api.service';

import { VolunteerTask } from '@app/entities/volunteer-task';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(
    private api : TasksApiService
  ) { }

  listTasks(skillSet? : string[], priority? : number, location? : string, keywords? : string) : Observable<VolunteerTask[]> {
    var filters = [];
    if(skillSet) {
      filters.push("SkillSet");
    }
    if(priority) {
      filters.push("Priority");
    }
    if(location) {
      filters.push("LocationDistance");
    }
    if(keywords){
      filters.push("Keywords");
    }
    const filterObj = new VolunteerTasksFilter(filters, skillSet, priority, location, keywords);
    return this.api.getTasks(filterObj).pipe(
      map(response => response.tasks.map(task => new VolunteerTask(task.id, task.name, task.location, task.need, task.description, task.taskOwnerId, task.skillsNeeded)))
    )
  }
}
