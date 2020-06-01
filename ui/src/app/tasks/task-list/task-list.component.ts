import { Component, OnInit } from '@angular/core';
import { TasksService } from '@app/tasks/tasks.service';
import { Observable } from 'rxjs';
import { VolunteerTask } from '@app/entities/volunteer-task';
import { User } from '@app/entities/user';
import { FormGroup, FormControl } from '@angular/forms';
import { VolunteerTasksFilter } from '@app/api/tasks-api.service';
import { LoginManagerService } from '@app/loginmanager.service';
@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  tasks : Observable<VolunteerTask[]>;
  user : User;

  filterForm : FormGroup

  constructor(
    private tasksService : TasksService,
    private loginmanager : LoginManagerService
  ) {
    this.filterForm = new FormGroup({
      'high-need': new FormControl(false),
      'low-need': new FormControl(false),
      'match-skillset': new FormControl(false),
      'location': new FormControl(false),
      'search': new FormControl()
    })
    this.loginmanager.isLoggedIn().subscribe(loggedIn => {
      if(loggedIn) {
        this.loginmanager.getLoggedInUser("/volunteer").subscribe(user => {
          this.user = user;
        })
      }
    })
  }


  ngOnInit(): void {
    this.tasks = this.tasksService.listTasks();
  }

  updateFilters() {
    const value = this.filterForm.value;
    const filter = new VolunteerTasksFilter([], [], -1, "", "")

    if(value['high-need'] && !value['low-need']) {
      filter.enabledFilters.push("need");
      filter.need = 1;
    } else if(!value['high-need'] && value['low-need']) {
      filter.enabledFilters.push("need");
      filter.need = 0;
    }

    if(value['match-skillset']) {
      filter.enabledFilters.push("skillSet");
      filter.skillSet = this.user.skillset;
    }

    if(value['location']) {
      filter.enabledFilters.push("location");
      filter.location = this.user.location;
    }

    if(value['search']) {
      filter.enabledFilters.push("search");
      filter.search = value['search'];
    }

    this.tasks = this.tasksService.listTasks(filter);
  }
  


}
