import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskListComponent } from './task-list.component';

import { VolunteerTask, VolunteerFilter, ApiService } from '@app/api.service';
import { of, Observable } from 'rxjs';

describe('TaskListComponent', () => {
  let component: TaskListComponent;
  let fixture: ComponentFixture<TaskListComponent>;

  var mockApi = {
    getVolunteerTasks(filters : VolunteerFilter[]) : Observable<VolunteerTask[]> {
      return of([
        {
          "id": 1,
          "name": "Supply Collector",
          "location": "Long Beach, CA",
          "need": 1,
          "description": "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
          "taskOwner": "Memorialcare Health System",
          "skillsNeeded": []
        },
        {
          "id": 2,
          "name": "Pamphlet Designer",
          "location": "Long Beach, CA",
          "need": 0,
          "description": "A graphic designer is needed to assist in the creation of informational brocures and pamphlets that will help inform the community on how to stay safe during the COVID pandemic.",
          "taskOwner": "Memorialcare Health System",
          "skillsNeeded": ["Graphic Design"]
        }
      ]);
    }
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [{ provide: ApiService, useValue: mockApi}],
      declarations: [ TaskListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
