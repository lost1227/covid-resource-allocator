import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import { UserInfoResponseModel } from '@app/api/userinfo-api.service'
import { ApiService, ResponseModel } from '@app/api/api.service';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from '@app/entities/user';

@Injectable({
  providedIn: 'root'
})
export class LoginApiService extends ApiService {

  constructor(
    private http : HttpClient
  ) {
    super();
  }

  checkIfLoggedIn() : Observable<UserInfoResponseModel> {
    return super.verifyResponse(this.http.get<UserInfoResponseModel>("/api/login"))
  }

  login(username : string, password : string ) : Observable<UserInfoResponseModel> {
    const headers = new HttpHeaders({
      Authorization: "Basic " + btoa(username + ":" + password)
    });
    return super.verifyResponse(this.http.get<UserInfoResponseModel>("/api/login", {headers: headers}));
  }

  registerNewUser(request : NewUserRequest) : Observable<ResponseModel> {
    return super.verifyResponse(this.http.post<ResponseModel>("/api/login/register", request));
  }
}

export class NewUserRequest {
  constructor(
    public name : string,
    public location : string,
    public userType : string,
    public description : string,
    public skillset : string[],
  
    public username : string,
    public password : string
  ) {}
}
