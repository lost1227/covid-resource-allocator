import { TestBed, getTestBed } from '@angular/core/testing';

import { SuppliesApiService } from './supplies-api.service';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';


describe('SuppliesApiService', () => {
  let injector: TestBed;
  let httpMock: HttpTestingController;
  let service: SuppliesApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SuppliesApiService]});
    injector = getTestBed();
    service = TestBed.inject(SuppliesApiService);
    httpMock = injector.get(HttpTestingController)
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
