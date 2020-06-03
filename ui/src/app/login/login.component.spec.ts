import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { FormBuilder } from '@angular/forms';
import { LoginManagerService } from '@app/loginmanager.service';
import { of } from 'rxjs';
import { Router } from '@angular/router';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  let mockRouter = {
    navigateByUrl(url : string) {}
  }

  let mockLoginManager = {
    login(username : string, password : string) {
      
    },
    isLoggedIn() {
      return of(false)
    },
    failedLogin : of(false)
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers : [FormBuilder, 
        {provide : LoginManagerService, useValue : mockLoginManager},
        {provide : Router, useValue : mockRouter}],
      declarations: [ LoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
