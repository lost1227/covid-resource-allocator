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

  getSupplies(filters? : SuppliesFilter) : Observable<SuppliesResponse> {
    if(filters == null) {
      filters = new SuppliesFilter([], null, 0, "", "");
    }
    return super.verifyResponse(this.http.post<SuppliesResponse>("/api/supplies", filters));
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

