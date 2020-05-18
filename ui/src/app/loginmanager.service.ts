import { Injectable } from '@angular/core';
import { User } from '@app/entities/User';
import { LoginApiService } from './api/login-api.service';
import { Router } from '@angular/router';
import { Observable, throwError, of, empty } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginManagerService {

  private loggedInUser : User = null;

  private redirect : string = "/"

  constructor(
    private loginApi : LoginApiService,
    private router : Router
  ) { }

  public startLogin() {
    this.redirect = this.router.url;
    this.router.navigateByUrl("/login");
  }

  public getLoggedInUser() : Observable<User> {

    if(this.loggedInUser == null) {
      const check : Observable<User> = this.loginApi.checkIfLoggedIn().pipe(
        catchError((error : HttpErrorResponse) => {
          if(error.status == 401) {
            this.startLogin();
          }
          return empty();
        })
      )
      check.subscribe(principal => {
        this.loggedInUser = principal
      });
      return check;
    } else {
      return of(this.loggedInUser);
    }
  }

  public login(username : string, password : string) {
    this.loginApi.login(username, password).subscribe(response => {
      this.loggedInUser = new User(response.id, response.name, response.location, response.userType, response.description, response.skillset);
      const tmp = this.redirect;
      this.redirect = "/";
      this.router.navigateByUrl(tmp);
    })
  }
}