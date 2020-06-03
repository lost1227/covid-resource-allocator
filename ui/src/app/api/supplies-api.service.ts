import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
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

  getSupplies(filters? : SuppliesFilter) : Observable<SuppliesResponse> {
    if(filters == null) {
      filters = new SuppliesFilter([], null, 0, "", "");
    }
    return super.verifyResponse(this.http.post<SuppliesResponse>("/api/supplies", filters));
  }

  getSupply(id : number) : Observable<SupplyResponse> {
    let params = new HttpParams();
    params = params.append("id", String(id));
    return super.verifyResponse(this.http.get<SupplyResponse>("/api/supply", {params : params}));
  }

  postSupplies(supply : PostSupplyRequestModel) : Observable<SupplyResponse> {
    return super.verifyResponse(this.http.post<SupplyResponse>("/api/supplies/post",supply))
  }
   
}

export class SuppliesFilter {
  constructor(
    public enabledFilters : string[],
    public type : SupplyType,
    public need : number,
    public location : string,
    public search : string
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
    public quantity : number,
    public photoId : number
  ) { }
}

export interface SupplyResponse extends ResponseModel {
  id : number
  name : string
  location : string
  need : number
  description : string
  ownerId : number
  type : SupplyType
  quantity : number
  photoId : number
}
export interface SuppliesResponse extends ResponseModel {
  supplies : SupplyResponse[]
}

