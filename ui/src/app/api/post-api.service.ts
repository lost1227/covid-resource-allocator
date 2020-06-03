import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { ApiService, ResponseModel } from '@app/api/api.service';
import { SupplyType } from '@app/entities/supply';
@Injectable({
  providedIn: 'root'
})
export class PostApiService extends ApiService {

  constructor(private http : HttpClient) {
    super();
  }

  PostSupply(supply : SupplyRequestModel) : Observable<ResponseModel> {
    return super.verifyResponse(this.http.post<ResponseModel>("/api/supply/post", supply))
  }

  listSupplies() : Observable<ListSupplyResponse> {
    return super.verifyResponse(this.http.get<ListSupplyResponse>("/api/supply/supplies"));
  }
}
//match fields 
export class PostSupplyRequestModel {
  constructor(
    public id : number,
    public  name : string,
    public location : string,
    public need : number,
    public description : string,
    public ownerId : number,
    public type : SupplyType,
    public quantity : number
  ) { }
}
export class SupplyRequestModel {
  constructor(
    public supplyId : number,
  ) { }
}


export interface SupplyResponse {
  id : number;
  name : string;
  location : string;
  need : number;
  description : string;
  taskOwner : string;

}


export interface ListSupplyResponse extends ResponseModel {
  id : number,
  supply : SupplyResponse[]
}
