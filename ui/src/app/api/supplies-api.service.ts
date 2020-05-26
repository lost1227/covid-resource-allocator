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

  getSupplies(filters : SuppliesFilter) : Observable<SuppliesResponse> {
    if(filters == null) {
      filters = new SuppliesFilter([], null, 0, 0);
    }
    return super.verifyResponse(this.http.post<SuppliesResponse>("/api/supplies", filters));
  }

  getSupply(id : number) : Observable<SupplyResponse> {
    let params = new HttpParams();
    params = params.append("id", String(id));
    return super.verifyResponse(this.http.get<SupplyResponse>("/api/supply", {params : params}));
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

export interface SupplyResponse extends ResponseModel {
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

