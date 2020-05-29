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

  logout() : Observable<string> {
    return this.http.get("/logout", {responseType: "text"});
  }

  registerNewUser(request : NewUserRequest) : Observable<ResponseModel> {
    return super.verifyResponse(this.http.post<ResponseModel>("/api/login/register", request));
  }

  editUser(request : EditUserRequest) : Observable<UserInfoResponseModel> {
    return super.verifyResponse(this.http.post<UserInfoResponseModel>("/api/login/edit", request));
  }
}

export class NewUserRequest {
  constructor(
    public name : string,
    public location : string,
    public userType : string,
    public description : string,
    public skillset : string[],

    public photoId : number,
  
    public username : string,
    public password : string
  ) {}
}

export class EditUserRequest {
  constructor(
    public name : string = "",
    public location : string = "",
    public description : string = "",
    public skillset : string[] = [],
    public password : string = "",
    public photoId : number = -1
  ) {}

  static editPhotoId(photoId : number) : EditUserRequest {
    return new EditUserRequest("", "", "", [], "", photoId);
  }
}
