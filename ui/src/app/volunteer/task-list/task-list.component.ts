import { Component, OnInit } from '@angular/core';
import { ApiService, VolunteerFilter, VolunteerTask } from '@app/api.service';
import { Observable } from 'rxjs';
import { ToolbarComponent } from '@app/toolbar/toolbar.component';

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
  filter : VolunteerFilter;

  value : string;

  constructor(private api : ApiService) { }

  public createFilter() : VolunteerFilter{
    this.filter = new VolunteerFilter(this.need, this.match, -1, this.value);
    return this.filter;
  }

  ngOnInit(): void {
    this.tasks = this.api.getVolunteerTasks(new VolunteerFilter(false, false, -1, ""));
    //this.tasks = this.api.getVolunteerTasks(this.filter);

  }

  onChange(){
    this.tasks = this.api.getVolunteerTasks(this.createFilter());
  }

  


}
