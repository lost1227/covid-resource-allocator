import { Component, ViewChild } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { TaskItemComponent } from './task-item.component';

describe('TaskItemComponent', () => {
  let component: TestHostComponent;
  let fixture: ComponentFixture<TestHostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestHostComponent, TaskItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestHostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display the title', () => {
    const titleDE = fixture.debugElement.query(By.css('.task-title'))
    expect(titleDE).toBeTruthy();
    const titleHTML = titleDE.nativeElement;
    expect(titleHTML.textContent).toEqual(component.testtask.name);
  })

  it('should display the description', () => {
    const descDE = fixture.debugElement.query(By.css('.task-description'))
    expect(descDE).toBeTruthy();
    const descHTML = descDE.nativeElement;
    expect(descHTML.textContent).toEqual(component.testtask.description);
  })

  it('should display the location', () => {
    const locDE = fixture.debugElement.query(By.css('.task-loc'))
    expect(locDE).toBeTruthy();
    const locHTML = locDE.nativeElement;
    expect(locHTML.textContent).toEqual(component.testtask.location);
  })

  it('should display the need', () => {
    const needHighDE = fixture.debugElement.query(By.css('.task-need-high'))
    expect(needHighDE).toBeTruthy();
    console.log(needHighDE);
    const needHighHTML = needHighDE.nativeElement;
    expect(needHighHTML.textContent).toEqual("High need");

    component.taskItem.task = {
      "id": 1,
      "name": "Supply Collector",
      "location": "Long Beach, CA",
      "need": 0,
      "description": "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
      "ownerId": 3,
      "skillNeeded": "",
      "photoId": -1
    }
    fixture.detectChanges();

    const needLowDE = fixture.debugElement.query(By.css('.task-need-low'))
    expect(needLowDE).toBeTruthy();
    const needLowHTML = needLowDE.nativeElement;
    expect(needLowHTML.textContent).toEqual("Low need");

    component.taskItem.task = {
      "id": 2,
      "name": "Supply Collector",
      "location": "Long Beach, CA",
      "need": 2,
      "description": "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
      "ownerId": 4,
      "skillNeeded": "Graphic Design",
      "photoId": -1
    }
    fixture.detectChanges();

    var needUndefinedDE = fixture.debugElement.query(By.css('.task-need-high'))
    expect(needUndefinedDE).toBeNull();
    needUndefinedDE = fixture.debugElement.query(By.css('.task-need-low'))
    expect(needUndefinedDE).toBeNull();
  })

  @Component({
    selector: 'host-component',
    template: '<app-task-item [task]="testtask"></app-task-item>'
  })
  class TestHostComponent {
    testtask = {
      "id": 1,
      "name": "Supply Collector",
      "location": "Long Beach, CA",
      "need": 1,
      "description": "Volunteers are needed to assist in the collection of donated supplies for distribution to medical facilities.",
      "taskOwner": "Memorialcare Health System"
    }

    @ViewChild(TaskItemComponent)
    public taskItem : TaskItemComponent
  }
});
