import { Injectable } from '@angular/core';
import { SuppliesApiService, SuppliesFilter } from '@app/api/supplies-api.service';

import { Supply } from '@app/entities/supply';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class SuppliesService {

  constructor(
    private api : SuppliesApiService
  ) { }

  listSupplies(filter? : SuppliesFilter) : Observable<Supply[]> {
    return this.api.getSupplies(filter).pipe(
      map(response => response.supplies.map(supply => new Supply(supply.id, supply.name, supply.location, supply.need, supply.description, supply.ownerId, supply.type, supply.quantity, supply.photoId)))
    )
  }

  getSupply(id : number) : Observable<Supply> {
    return this.api.getSupply(id).pipe(
      map(response => new Supply(response.id, response.name, response.location, response.need, response.description, response.ownerId, response.type, response.quantity, response.photoId))
    )
  }
}
