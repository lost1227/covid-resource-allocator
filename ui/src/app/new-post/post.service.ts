import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { LoginManagerService } from '@app/loginmanager.service';
import { SuppliesApiService } from '@app/api/supplies-api.service';
import { Observable } from 'rxjs';
import { User } from '@app/entities/user';
import { Supply } from '@app/entities/supply';
import { VolunteerTask } from '@app/entities/volunteer-task';
import { TasksApiService } from '@app/api/tasks-api.service';
import { PhotosApiService } from '@app/api/photos-api.service';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(
    private router : Router,
    private loginManager : LoginManagerService,
    private photoService : PhotosApiService,
    private supplyService : SuppliesApiService,
    private tasksService : TasksApiService
  ) { }

  login(type : PostType) : Observable<User> {
    return this.loginManager.getLoggedInUser("/post/"+type);
  }

  postSupply(supply : Supply, photo : File) {
    this.photoService.postPhoto(photo).subscribe(photoResponse => {
      supply = new Supply(supply.id, supply.name, supply.location, supply.need, supply.description, supply.ownerId, supply.type, supply.quantity, photoResponse.id);
      this.supplyService.postSupplies(supply).subscribe(postResponse => {
        // TODO: navigate to details page
        this.router.navigateByUrl("/");
      });
    })
  }

  postTask(task : VolunteerTask, photo : File) {
    this.photoService.postPhoto(photo).subscribe(photoResponse => {
      task.photoId = photoResponse.id;
      this.tasksService.postNewTask(task).subscribe(postResponse => {
        // TODO: navigate to details page
        this.router.navigateByUrl("/");
      })
    })
  }
}

export enum PostType {
  VOLUNTEER = "volunteer",
  SUPPLY = "supply"
}
