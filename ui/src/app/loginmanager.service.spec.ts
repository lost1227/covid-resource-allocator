import { TestBed } from '@angular/core/testing';

import { LoginManagerService } from './loginmanager.service';
import { Observable, of } from 'rxjs';
import { UserInfoResponseModel } from './api/userinfo-api.service';
import { LoginApiService } from './api/login-api.service';
import { Router } from '@angular/router';

describe('LoginmanagerService', () => {
  let service: LoginManagerService;

  let mockLoginApi = {
    login(username : string, password : string ) : Observable<UserInfoResponseModel> {
      return of({
        ok : true,
        id : 0,
        name : "Test User",
        location : "somewhere",
        userType : "sometype",
        description : "A user for testing",
        skillset : []
      });
    }
  }

  let mockRouter = {
    url : "/",
    navigateByUrl(loc : string) {

    }
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers : [{ provide : LoginApiService, useValue : mockLoginApi},
                  { provide : Router, useValue : mockRouter} ]
    });
    service = TestBed.inject(LoginManagerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
