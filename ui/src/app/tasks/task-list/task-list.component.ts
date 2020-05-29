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

  tasks : Observable<VolunteerTask[]>;
  //mockTasks : VolunteerTask[];
  //task : VolunteerTask;
  need : boolean = false;
  match : boolean = false;
  loc : boolean = false;
  //filter : VolunteerFilter;

  value : string;

  constructor(private tasksService : TasksService) { }

  /*public createFilter() : VolunteerFilter{
    this.filter = new VolunteerFilter(this.need, this.match, this.loc, this.value);
    return this.filter;
  }*/

  ngOnInit(): void {
    //this.tasks = this.api.getVolunteerTasks(new VolunteerFilter(false, false, false, ""));
    //this.tasks = this.api.getVolunteerTasks(this.filter);

    this.tasks = this.tasksService.listTasks();
  }

  /*onChange(){
    this.tasks = this.api.getVolunteerTasks(this.createFilter());
  }*/

  


}
