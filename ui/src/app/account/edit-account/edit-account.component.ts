import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '@app/entities/user';
import { LoginManagerService } from '@app/loginmanager.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PhotosApiService } from '@app/api/photos-api.service';
import { EditUserRequest } from '@app/api/login-api.service';

@Component({
  selector: 'app-account',
  templateUrl: './edit-account.component.html',
  styleUrls: ['./edit-account.component.css']
})
export class EditAccountComponent implements OnInit {

  user : User


  form : FormGroup

  constructor(
    private route : ActivatedRoute,
    private login : LoginManagerService,
    private photos : PhotosApiService
  ) {
    this.route.url.subscribe(params => {
      this.login.getLoggedInUser("/profile/edit").subscribe(user => {
        this.user = user;

        this.form = new FormGroup({
          'name': new FormControl(user.name, Validators.required),
          'description': new FormControl(user.description, Validators.required),
          'location': new FormControl(user.location, Validators.required),
          'image': new FormControl(null),
          'password': new FormControl(''),
          'passwordConf': new FormControl('')
        })
      })
    })
  }

  getPhotoUrl() {
    if(this.user && this.user.photoId == -1) {
      return "/assets/add.png"
    } else {
      return '/api/photo/get?id='+this.user.photoId;
    }
  }

  ngOnInit(): void {
  }

  onSubmit(formValue : any) {
    console.log(formValue);

    let options = new EditUserRequest();

    if(formValue.name !== this.user.name) {
      options.name = formValue.name;
    }

    if(formValue.location !== this.user.location) {
      options.location = formValue.location;
    }

    if(formValue.description !== this.user.description) {
      options.description = formValue.description;
    }

    if(formValue.password && formValue.password === formValue.passwordConf) {
      options.password = formValue.password;
    }

    if(formValue.image != null) {
      this.photos.postPhoto(formValue.image).subscribe(response => {
        options.photoId = response.id;
        
        this.login.editUser(options);
      })
    } else {
      this.login.editUser(options);
    }

  }

}
