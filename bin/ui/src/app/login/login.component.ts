import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { LoginManagerService } from '@app/loginmanager.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm : FormGroup

  showFailedLogin : boolean = false

  constructor(
    private loginManager : LoginManagerService,
    private formBuilder : FormBuilder
  ) {
    this.loginForm = formBuilder.group({
      username : '',
      password : ''
    })
    loginManager.failedLogin.subscribe(failed => {
      this.showFailedLogin = failed
    })
  }

  ngOnInit(): void {
  }

  onSubmit(formValue) {
    // TODO: Catch invalid username/password
    this.loginForm.reset();
    this.loginManager.login(formValue.username, formValue.password);
  }

}
