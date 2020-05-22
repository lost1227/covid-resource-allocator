import { TestBed, getTestBed } from '@angular/core/testing';

import { TasksApiService } from './tasks-api.service';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';

describe('TasksApiService', () => {
  let injector: TestBed;
  let httpMock: HttpTestingController;
  let service: TasksApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TasksApiService]});
    injector = getTestBed();
    service = TestBed.inject(TasksApiService);
    httpMock = injector.get(HttpTestingController)
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
