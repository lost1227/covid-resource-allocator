import { Component, OnInit } from '@angular/core';

import { FormGroup, FormControl, Validators } from '@angular/forms';
import { User } from '@app/entities/user';
import { LoginManagerService } from '@app/loginmanager.service';
import { Router } from '@angular/router';
import { PhotosApiService } from '@app/api/photos-api.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  form : FormGroup

  imgSrc : string = "/assets/add.png"

  constructor(
    private router : Router,
    private loginmanager : LoginManagerService
  ) {
    this.form = new FormGroup({
      'name' : new FormControl('', Validators.required),
      'photo' : new FormControl(null, Validators.required),
      'accountType': new FormControl('', Validators.required),
      'description' : new FormControl('', Validators.required),
      'location' : new FormControl('', Validators.required),
      'username' : new FormControl('', Validators.required),
      'password' : new FormControl('', Validators.required),
      'passwordConf' : new FormControl('', Validators.required)
    })
  }

  ngOnInit(): void {
    this.loginmanager.isLoggedIn().subscribe(loggedIn => {
      if(loggedIn) {
        this.router.navigateByUrl("/");
      }
    })
  }

  onSubmit(formValue) : void {
    console.log(formValue);
    if(formValue.photo 
      && formValue.name 
      && formValue.location 
      && formValue.accountType 
      && formValue.description 
      && formValue.username
      && formValue.password
      && formValue.passwordConf
      && formValue.password == formValue.passwordConf ) {
        const newUser = new User(-1, formValue.name, formValue.location, formValue.accountType, formValue.description, [], -1);
        this.loginmanager.registerNewUser(formValue.username, formValue.password, newUser, formValue.photo)
    }
  }

}
