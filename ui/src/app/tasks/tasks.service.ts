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

  listTasks(skillSet? : string[], priority? : number, distance? : number) : Observable<VolunteerTask[]> {
    var filters = [];
    if(skillSet) {
      filters.push("SkillSet");
    }
    if(priority) {
      filters.push("Priority");
    }
    if(distance) {
      filters.push("LocationDistance");
    }
    const filterObj = new VolunteerTasksFilter(filters, skillSet, priority, distance);
    return this.api.getTasks(filterObj).pipe(
      map(response => response.tasks.map(task => new VolunteerTask(task.id, task.name, task.location, task.need, task.description, task.ownerId, task.photoId)))
    )
  }
}
