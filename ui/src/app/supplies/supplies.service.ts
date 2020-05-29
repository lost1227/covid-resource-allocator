import { Injectable } from '@angular/core';
import { SuppliesApiService, SuppliesFilter } from '@app/api/supplies-api.service';

import { Supply, SupplyType } from '@app/entities/supply';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class SuppliesService {

  constructor(
    private api : SuppliesApiService
  ) { }

  listSupplies(type? : SupplyType, priority? : number, distance? : number) : Observable<Supply[]> {
    var filters = [];
    if(type) {
      filters.push("SupplyType");
    }
    if(priority) {
      filters.push("Priority");
    }
    if(distance) {
      filters.push("LocationDistance");
    }
    const filterObj = new SuppliesFilter(filters, type, priority, distance);
    return this.api.getSupplies(filterObj).pipe(
      map(response => response.supplies.map(supply => new Supply(supply.id, supply.name, supply.location, supply.need, supply.description, supply.ownerId, supply.type, supply.quantity, supply.photoId)))
    )
  }
}
