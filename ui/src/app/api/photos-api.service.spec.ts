import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';

import { PhotosApiService } from './photos-api.service';

describe('PhotosApiService', () => {
  let injector: TestBed;
  let httpMock: HttpTestingController;
  let service: PhotosApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PhotosApiService]});
    injector = getTestBed();
    service = TestBed.inject(PhotosApiService);
    httpMock = injector.get(HttpTestingController)
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
