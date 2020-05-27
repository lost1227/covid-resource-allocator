import { Component, OnInit } from '@angular/core';
import { ApiService, VolunteerFilter, VolunteerTask } from '@app/api.service';
import { Observable } from 'rxjs';
<<<<<<< HEAD
import {PageEvent} from '@angular/material/paginator';
=======
import { ToolbarComponent } from '@app/toolbar/toolbar.component';
>>>>>>> 788c63d... Updated ViewTasks

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks : Observable<VolunteerTask[]>;
<<<<<<< HEAD
  mockTasks : VolunteerTask[];
  //task : VolunteerTask;
=======
  need : boolean = false;
  match : boolean = false;
  filter : VolunteerFilter;

  value : string;
>>>>>>> 788c63d... Updated ViewTasks

  constructor(private api : ApiService) { }

  public createFilter() : VolunteerFilter{
    this.filter = new VolunteerFilter(this.need, this.match, -1, this.value);
    return this.filter;
  }

  ngOnInit(): void {
<<<<<<< HEAD
    this.mockTasks = ([
      {
        "id": 1,
        "name": "Supply Collector",
        "location": "Long Beach, CA",
        "need": 1,
        "description": "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
        "taskOwner": "Memorialcare Health System"
      },
      {
        "id": 2,
        "name": "Pamphlet Designer",
        "location": "Long Beach, CA",
        "need": 0,
        "description": "A graphic designer is needed to assist in the creation of informational brocures and pamphlets that will help inform the community on how to stay safe during the COVID pandemic.",
        "taskOwner": "Blue Shield of California"

      }
    ])
    this.tasks = this.api.getVolunteerTasks(new VolunteerFilter(false, false, -1));
  }

=======
    this.tasks = this.api.getVolunteerTasks(new VolunteerFilter(false, false, -1, ""));
    //this.tasks = this.api.getVolunteerTasks(this.filter);

  }

  onChange(){
    this.tasks = this.api.getVolunteerTasks(this.createFilter());
  }

>>>>>>> 788c63d... Updated ViewTasks
  


}
