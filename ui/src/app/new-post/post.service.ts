import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { LoginManagerService } from '@app/loginmanager.service';
import { SuppliesApiService } from '@app/api/supplies-api.service';
import { Observable } from 'rxjs';
import { User } from '@app/entities/user';
import { Supply } from '@app/entities/supply';
import { VolunteerTask } from '@app/entities/volunteer-task';
import { TasksApiService } from '@app/api/tasks-api.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(
    private router : Router,
    private loginManager : LoginManagerService,
    private supplyService : SuppliesApiService,
    private tasksService : TasksApiService
  ) { }

  login(type : PostType) : Observable<User> {
    return this.loginManager.getLoggedInUser("/post/"+type);
  }

  postSupply(supply : Supply) {
    this.supplyService.postSupplies(supply).subscribe(response => {
      this.router.navigateByUrl("/");
    });
  }

  postTask(task : VolunteerTask) {
    this.tasksService.postNewTask(task).subscribe(response => {
      this.router.navigateByUrl("/");
    })
  }
}

export enum PostType {
  VOLUNTEER = "volunteer",
  SUPPLY = "supply"
}
