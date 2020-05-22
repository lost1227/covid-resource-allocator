import { Component, OnInit } from '@angular/core';
import { TasksService } from '@app/tasks/tasks.service';
import { Observable } from 'rxjs';
import { VolunteerTask } from '@app/entities/volunteer-task';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks : Observable<VolunteerTask[]>

  constructor(private tasksService : TasksService) { }

  ngOnInit(): void {
    this.tasks = this.tasksService.listTasks();
  }

}
