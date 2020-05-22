import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import { UserInfoResponseModel } from '@app/api/userinfo-api.service'
import { ApiService } from '@app/api/api.service';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

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

}
