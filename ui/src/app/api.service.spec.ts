import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ApiService, VolunteerFilter } from '@app/api.service';

describe('ApiService', () => {
  let injector: TestBed;
  let service: ApiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ApiService]
    });
    injector = getTestBed();
    service = TestBed.inject(ApiService);
    httpMock = injector.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  })

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return an Observable<VolunteerTask[]>', () => {
    const dummyTasks = [
      {
        "id": 1,
        "name": "Supply Collector",
        "location": "Long Beach, CA",
        "need": 1,
        "description": "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
        "taskOwner": "Memorialcare Health System"
      },
      {
        "id": 2,
        "name": "Pamphlet Designer",
        "location": "Long Beach, CA",
        "need": 0,
        "description": "A graphic designer is needed to assist in the creation of informational brocures and pamphlets that will help inform the community on how to stay safe during the COVID pandemic.",
        "taskOwner": "Blue Shield of California"
      }
    ];
    
    service.getVolunteerTasks(new VolunteerFilter(false, false, -1)).subscribe(tasks => {
      expect(tasks.length).toBe(dummyTasks.length);
      expect(tasks).toEqual(dummyTasks);
    });

    const req = httpMock.expectOne("/api/tasks");
    expect(req.request.method).toBe("POST");
    req.flush(dummyTasks);
  })
});
