import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiService, ResponseModel } from '@app/api/api.service';
import { Observable } from 'rxjs';
import { SupplyType } from '@app/entities/supply';


@Injectable({
  providedIn: 'root'
})
export class SuppliesApiService extends ApiService  {

  constructor(
    private http : HttpClient
  ) {
    super();
  }

  getSupplies(filters : SuppliesFilter) : Observable<SuppliesResponse> {
    if(filters == null) {
      filters = new SuppliesFilter([], null, 0, 0);
    }
    return super.verifyResponse(this.http.post<SuppliesResponse>("/api/supplies", filters));
  }
  //create post supply method
  postSupplies(supply : PostSupplyRequestModel) : Observable<ResponseModel> {
    return super.verifyResponse(this.http.post<ResponseModel>("/api/post/supply",supply))
  }
   
}

export class SuppliesFilter {
  constructor(
    public enabledFilters : string[],
    public type : SupplyType,
    public priority : number,
    public locationDistance : number
  ) {}
}
export class PostSupplyRequestModel {
  constructor(
    public  name : string,
    public location : string,
    public need : number,
    public description : string,
    public ownerId : number,
    public type : SupplyType,
    public quantity : number
  ) { }
}

export interface SupplyResponse {
  id : number
  name : string
  location : string
  need : number
  description : string
  ownerId : number
  type : SupplyType
  quantity : number
}
export interface SuppliesResponse extends ResponseModel {
  supplies : SupplyResponse[]
}

