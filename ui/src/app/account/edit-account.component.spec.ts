import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAccountComponent } from './edit-account.component';
import { of, Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { LoginManagerService } from '@app/loginmanager.service';
import { User } from '@app/entities/user';
import { FormBuilder } from '@angular/forms';

describe('EditAccountComponent', () => {
  let component: EditAccountComponent;
  let fixture: ComponentFixture<EditAccountComponent>;

  let mockRoute = {
    url : of("account")
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
      declarations: [ EditAccountComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
