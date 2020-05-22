import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPostComponent } from './new-post.component';
import { of, Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { LoginManagerService } from '@app/loginmanager.service';
import { User } from '@app/entities/user';
import { FormBuilder } from '@angular/forms';

describe('NewPostComponent', () => {
  let component: NewPostComponent;
  let fixture: ComponentFixture<NewPostComponent>;

  let mockRoute = {
    url : of("supply")
  }

  let mockloginservice = {
    getLoggedInUser() : Observable<User> {
      return of(new User(0, "Test User", "Test Location", "Test Type", "Test Description", []))
    }
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers : [{provide : ActivatedRoute, useValue : mockRoute},
                  {provide : LoginManagerService, useValue : mockloginservice},
                  FormBuilder],
      declarations: [ NewPostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
