import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup } from '@angular/forms';
import { User } from '@app/entities/user';
import { LoginManagerService } from '@app/loginmanager.service';
import { Router } from '@angular/router';


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
    private loginmanager : LoginManagerService,
    private formBuilder : FormBuilder
  ) {
    this.form = formBuilder.group({
      'name' : '',
      'photo' : null,
      'accountType': '',
      'description' : '',
      'location' : '',
      'username' : '',
      'password' : ''
    })
  }

  ngOnInit(): void {
    this.loginmanager.isLoggedIn().subscribe(loggedIn => {
      if(loggedIn) {
        this.router.navigateByUrl("/");
      }
    })
  }

  photoChange(event) : void {
    const reader = new FileReader();

    reader.onload = (e) => {
      this.imgSrc = String(e.target.result);
    }

    reader.readAsDataURL(event.target.files[0]);
  }

  onSubmit(formValue) : void {
    console.log(formValue);
    if(formValue.name && formValue.location && formValue.accountType && formValue.description) {
      const newUser = new User(-1, formValue.name, formValue.location, formValue.accountType, formValue.description, []);
      this.loginmanager.registerNewUser(formValue.username, formValue.password, newUser);
    }
  }

}
