import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { FormBuilder } from '@angular/forms';
import { LoginManagerService } from '@app/loginmanager.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  let mockLoginManager = {
    login(username : string, password : string) {
      
    }
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers : [FormBuilder, {provide : LoginManagerService, useValue : mockLoginManager}],
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
