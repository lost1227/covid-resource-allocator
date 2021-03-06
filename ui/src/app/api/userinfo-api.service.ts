import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs';
import { ApiService, ResponseModel } from '@app/api/api.service';

@Injectable({
  providedIn: 'root'
})
export class UserinfoApiService extends ApiService {

  constructor(private http : HttpClient) {
    super()
  }

  getUserInfo(id : number) : Observable<UserInfoResponseModel> {
    let params = new HttpParams();
    params = params.append("id", String(id));
    return super.verifyResponse(this.http.get<UserInfoResponseModel>("/api/user/info", { params: params }));
  }

  findUsersByName(name : string) : Observable<FindUsersResponseModel> {
    let params = new HttpParams();
    params = params.append("name", name);
    return super.verifyResponse(this.http.get<FindUsersResponseModel>("/api/user/find", { params: params }));
  }
}

export interface UserInfoResponseModel extends ResponseModel {
  id : number;
  name : string;
  location : string;
  userType : string;
  description : string;
  skillset : string[];
  photoId : number;
}
export interface FindUsersResponseModel extends ResponseModel {
  query : string,
  users : UserInfoResponseModel[]
}
