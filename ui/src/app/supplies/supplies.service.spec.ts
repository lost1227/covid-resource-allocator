import { TestBed } from '@angular/core/testing';

import { SuppliesService } from './supplies.service';
import { SuppliesResponse, SuppliesFilter, SuppliesApiService } from '@app/api/supplies-api.service';
import { empty, Observable } from 'rxjs';

describe('SuppliesService', () => {
  let service: SuppliesService;

  let mockApi = {
    getSupplies(filters : SuppliesFilter) : Observable<SuppliesResponse> {
      return empty();
    }
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [{provide : SuppliesApiService, useValue : mockApi}]
    });
    service = TestBed.inject(SuppliesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
