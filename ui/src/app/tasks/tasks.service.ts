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

  listTasks(filter? : VolunteerTasksFilter) : Observable<VolunteerTask[]> {
    if(filter == null) {
      filter = new VolunteerTasksFilter([], [], -1, "", "")
    }
    return this.api.getTasks(filter).pipe(
      map(response => response.tasks.map(task => new VolunteerTask(task.id, task.name, task.location, task.need, task.description, task.ownerId, task.skillNeeded, task.photoId)))
    )
  }

  getTask(id : number) : Observable<VolunteerTask> {
    return this.api.getTask(id).pipe(
      map(response => new VolunteerTask(response.id, response.name, response.location, response.need, response.description, response.ownerId, response.skillNeeded, response.photoId))
    )
  }
}
