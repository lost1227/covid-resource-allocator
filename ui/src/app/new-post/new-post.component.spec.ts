import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPostComponent } from './new-post.component';
import { of, Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { LoginManagerService } from '@app/loginmanager.service';
import { User } from '@app/entities/user';
import { FormBuilder } from '@angular/forms';
import { PostSupplyRequestModel, SuppliesApiService } from '@app/api/supplies-api.service';
import { ResponseModel } from '@app/api/api.service';

describe('NewPostComponent', () => {
  /*let component: NewPostComponent;
  let fixture: ComponentFixture<NewPostComponent>;

  let mockRoute = {
    url : of("supply")
  }

  let mockloginservice = {
    getLoggedInUser() : Observable<User> {
      return of(new User(0, "Test User", "Test Location", "Test Type", "Test Description", [], -1))
    }
  }

  let mockSupplyService = {
    postSupplies(supply : PostSupplyRequestModel) : Observable<ResponseModel> {
      return of({ok : true});
    }
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers : [{provide : ActivatedRoute, useValue : mockRoute},
                  {provide : LoginManagerService, useValue : mockloginservice},
                  {provide : SuppliesApiService, useValue: mockSupplyService},
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
  });*/
});
