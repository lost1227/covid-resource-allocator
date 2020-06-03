import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { ToolbarComponent } from './toolbar.component';
import { UserInfoResponseModel } from '@app/api/userinfo-api.service';
import { Observable, of } from 'rxjs';
import { LoginManagerService } from '@app/loginmanager.service';

describe('ToolbarComponent', () => {
  let component: ToolbarComponent;
  let fixture: ComponentFixture<ToolbarComponent>;

  let mockuserapi = {
    isLoggedIn() : Observable<Boolean> {
      return of(false);
    },
    getUserInfo(id : number) : Observable<UserInfoResponseModel> {
      return of({
        ok: true,
        id: id,
        name: "User "+id,
        location : "someplace",
        userType : "volunteer",
        description : "",
        skillset : ["unskilled"],
        photoId: -1
      })
    }
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [{provide: LoginManagerService, useValue: mockuserapi}],
      declarations: [ ToolbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('shoud set the selected class on the selected element', () => {
    component.selectedOption = component.options[0].label;
    fixture.detectChanges();

    const selectedButtonDE = fixture.debugElement.query(By.css('.nav-button-selected'))
    const selectedButtonHTML = selectedButtonDE.nativeElement;
    expect(selectedButtonHTML.textContent).toEqual(component.options[0].label);
  })
});
