import { TestBed, getTestBed } from '@angular/core/testing';

import { LoginApiService } from './login-api.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('LoginApiService', () => {
  let injector: TestBed;
  let httpMock: HttpTestingController;
  let service: LoginApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [LoginApiService]});
    injector = getTestBed();
    service = TestBed.inject(LoginApiService);
    httpMock = injector.get(HttpTestingController)
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
