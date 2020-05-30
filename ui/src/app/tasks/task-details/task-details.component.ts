import { Component, OnInit } from '@angular/core';
import { User } from '@app/entities/user';
import { VolunteerTask } from '@app/entities/volunteer-task';
import { TasksService } from '@app/tasks/tasks.service';
import { ActivatedRoute } from '@angular/router';
import { UserinfoApiService } from '@app/api/userinfo-api.service';

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent implements OnInit {

  user : User
  item : VolunteerTask

  constructor(
    private route : ActivatedRoute,
    private tasksService : TasksService,
    private usersService : UserinfoApiService
  ) {
    this.route.paramMap.subscribe( params => {
      const id = Number(params.get('id'));
      this.tasksService.getTask(id).subscribe(task => {
        this.item = task;
        this.usersService.getUserInfo(this.item.ownerId).subscribe(response => {
          this.user = new User(response.id, response.name, response.location, response.userType, response.description, response.skillset, response.photoId);
        })
      })
    }
    )
  }

  ngOnInit(): void {
  }

  getNeedClass() : string[] {
    if(!this.item) {
      return []
    }

    switch(this.item.need) {
      case 0:
        return ["task-need", "task-need-low"]
      case 1:
        return ["task-need", "task-need-high"]
      default:
        return []
    }
  }

  getNeedText() : string {
    if(!this.item) {
      return ""
    }

    switch(this.item.need) {
      case 0:
        return "Low need"
      case 1:
        return "High need"
      default:
        return ""
    }
  }

}
