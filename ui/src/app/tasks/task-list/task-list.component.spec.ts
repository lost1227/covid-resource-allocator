import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskListComponent } from './task-list.component';

import { of, Observable } from 'rxjs';
import { VolunteerTask } from '@app/entities/volunteer-task';
import { TasksService } from '../tasks.service';

describe('TaskListComponent', () => {
  let component: TaskListComponent;
  let fixture: ComponentFixture<TaskListComponent>;

  var mockTasksService = {
    listTasks(skillSet? : string[], priority? : number, distance? : number) : Observable<VolunteerTask[]> {
      return of([
        {
          "id": 1,
          "name": "Supply Collector",
          "location": "Long Beach, CA",
          "need": 1,
          "description": "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
          "ownerId": 3,
          "skillNeeded": "",
          "photoId": -1
        },
        {
          "id": 2,
          "name": "Pamphlet Designer",
          "location": "Long Beach, CA",
          "need": 0,
          "description": "A graphic designer is needed to assist in the creation of informational brocures and pamphlets that will help inform the community on how to stay safe during the COVID pandemic.",
          "ownerId": 4,
          "skillNeeded": "Graphic Design",
          "photoId": -1
        }
      ]);
    }
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [{ provide: TasksService, useValue: mockTasksService}],
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
