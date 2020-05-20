import { Component, OnInit } from '@angular/core';
import { ApiService, VolunteerFilter, VolunteerTask } from '@app/api.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks : Observable<VolunteerTask[]>

  constructor(private api : ApiService) { }

  ngOnInit(): void {
    this.tasks = this.api.getVolunteerTasks(new VolunteerFilter(false, false, -1));
  }

}
