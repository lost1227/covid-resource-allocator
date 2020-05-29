import { Injectable } from '@angular/core';
import { User } from '@app/entities/user';
import { LoginApiService, NewUserRequest } from './api/login-api.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, throwError, of, empty, Subject } from 'rxjs';
import { map } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginManagerService {

  private loggedInUser : User = null;

  private redirect : string = "/"

  failedLogin : Subject<boolean> = new Subject()

  private loggingIn = false;

  constructor(
    private loginApi : LoginApiService,
    private router : Router,
    private route : ActivatedRoute
  ) {
    route.queryParams.subscribe(params => {
      if(params["redirect"]) {
        this.redirect = params["redirect"];
      }
    })
  }

  public startLogin(redirect : string) {
    this.loggingIn = true;
    this.router.navigateByUrl("/login?redirect=" + redirect);
  }

  public isLoggedIn() : Observable<Boolean> {
    if(this.loggingIn) {
      return of(false);
    }
    if(this.loggedInUser == null) {
      return this.loginApi.checkIfLoggedIn().pipe(
        map(user => {
          if(user) {
            this.loggedInUser = user;
            return true;
          }
          return false;
        }),
        catchError((error : HttpErrorResponse) => {
          if(error.status == 401) {
            return of(false);
          }
          return throwError(error);
        })
      )
    } else {
      return of(true);
    }
  }

  public getLoggedInUser(redirect = "/") : Observable<User> {

    if(this.loggingIn) {
      return empty();
    }

    if(this.loggedInUser == null) {
      const check : Observable<User> = this.loginApi.checkIfLoggedIn().pipe(
        catchError((error : HttpErrorResponse) => {
          if(error.status == 401) {
            this.startLogin(redirect);
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
    this.loginApi.login(username, password).pipe(
      catchError((error : HttpErrorResponse) => {
        if(error.status == 401) {
          this.failedLogin.next(true);
          return empty();
        } else {
          return throwError(error);
        }
      })
    ).subscribe(response => {
      this.loggedInUser = new User(response.id, response.name, response.location, response.userType, response.description, response.skillset);
      this.failedLogin.next(false);
      this.loggingIn = false;
      const tmp = this.redirect;
      this.redirect = "/";
      this.router.navigateByUrl(tmp);
    })
  }

  public registerNewUser(username : string, password : string, user : User) {
    const request = new NewUserRequest(user.name, user.location, user.userType, user.description, user.skillset, username, password);
    this.loginApi.registerNewUser(request).subscribe(response => {
      const sub = this.failedLogin.subscribe(failed => {
        if(failed) {
          console.error("Created user "+username+" but failed to log in!");
          this.router.navigateByUrl("/login");
        }
        sub.unsubscribe();
      })
      this.login(username, password);
    })
  }
}
