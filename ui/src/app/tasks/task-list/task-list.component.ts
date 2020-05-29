import { Component, OnInit } from '@angular/core';
import { TasksService } from '@app/tasks/tasks.service';
import { Observable } from 'rxjs';
import { VolunteerTask } from '@app/entities/volunteer-task';
import { User } from '@app/entities/user';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks : Observable<VolunteerTask[]>;
  user : User;
  //mockTasks : VolunteerTask[];
  //task : VolunteerTask;
  need : boolean = false;
  match : boolean = false;
  loc : boolean = false;

  skillset : string[];
  priority : number;
  location : string;
  keywords : string;
  //filter : VolunteerFilterRequestModel;

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

  onChange(){
    /*if(this.need){
      this.priority = 1
    }
    if(this.match){
      this.skillset = this.user.skillset
    }
    if(this.loc){
      this.location = this.user.location
    }

    this.tasks = this.tasksService.listTasks(this.skillset, this.priority, this.location, this.value);
    //getVolunteerTasks(this.createFilter());*/
  }

  


}
