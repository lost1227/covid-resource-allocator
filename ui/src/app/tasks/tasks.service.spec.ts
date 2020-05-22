import { TestBed } from '@angular/core/testing';

import { TasksService } from './tasks.service';
import { VolunteerTasksResponse, VolunteerTasksFilter, TasksApiService } from '@app/api/tasks-api.service';
import { Observable, empty } from 'rxjs';

describe('TasksService', () => {
  let service: TasksService;

  let mockApi = {
    getTasks(filters : VolunteerTasksFilter) : Observable<VolunteerTasksResponse> {
      return empty();
    }
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [{provide : TasksApiService, useValue : mockApi}]
    });
    service = TestBed.inject(TasksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
