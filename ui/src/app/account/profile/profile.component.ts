import { Component, OnInit } from '@angular/core';
import { UserinfoApiService } from '@app/api/userinfo-api.service';
import { LoginManagerService } from '@app/loginmanager.service';
import { User } from '@app/entities/user';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user : User

  showEdit : boolean

  constructor(
    private route : ActivatedRoute,
    private userapi : UserinfoApiService,
    private loginmanger : LoginManagerService
  ) {
    this.route.paramMap.subscribe( params => {
      const id = Number(params.get('id'));
      if(isNaN(id)) {
        this.user = null;
        return;
      }
      this.userapi.getUserInfo(id).pipe(
        map(response => new User(response.id, response.name, response.location, response.userType, response.description, response.skillset, response.photoId))
      ).subscribe(user => {
        this.user = user;

        this.loginmanger.isLoggedIn().subscribe(loggedIn => {
          if(loggedIn) {
            this.loginmanger.getLoggedInUser().subscribe(loggedInUser => {
              this.showEdit = user.id == loggedInUser.id;
            })
          }
        })

      })
    })
  }

  ngOnInit(): void {

  }

}
