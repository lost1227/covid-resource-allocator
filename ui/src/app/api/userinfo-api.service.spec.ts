import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { UserinfoApiService } from './userinfo-api.service';

describe('UserinfoApiService', () => {
  let injector: TestBed;
  let httpMock: HttpTestingController;
  let service: UserinfoApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserinfoApiService]});
    injector = getTestBed();
    service = TestBed.inject(UserinfoApiService);
    httpMock = injector.get(HttpTestingController)
  });

  afterEach(() => {
    httpMock.verify();
  })

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return a UserInfoResponseModel', () => {
    const userInfo = {
      ok: true,
      id: 0,
      name: "Memorialcare Health System",
      location: "Long Beach, CA",
      userType: "provider",
      description: "Hospital located in Long Beach, California",
      skillset: ["n/a"],
      photoId: -1
    }

    service.getUserInfo(0).subscribe(result => {
      expect(result).toEqual(userInfo);
    });

    const req = httpMock.expectOne("/api/user/info?id=0");
    expect(req.request.method).toBe("GET");
    expect(req.request.params.has("id")).toBeTrue();
    expect(req.request.params.get("id")).toEqual("0");
    req.flush(userInfo);
  });
});
