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
      map(response => response.tasks.map(task => new VolunteerTask(task.id, task.name, task.location, task.need, task.description, task.taskOwnerId, task.skillNeeded)))
    )
  }
}
