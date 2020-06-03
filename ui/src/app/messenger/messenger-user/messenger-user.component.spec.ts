import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessengerUserComponent } from './messenger-user.component';
import { Observable, Subject} from 'rxjs';
import { of } from 'rxjs';
import { Conversation, MessageUser, Message, MessengerService } from '@app/messenger/messenger.service';
import { ViewChild, Component } from '@angular/core';
import { By } from '@angular/platform-browser';

describe('MessengerUserComponent', () => {
  let component: TestHostComponent;
  let fixture: ComponentFixture<TestHostComponent>;

  let mockMessengerService = {
    listConversations() : Observable<Conversation[]> {
      return of([])
    },
    getMessageUser(id : number) : Observable<MessageUser> {
      return of()
    },

    selectedConversation : new Subject<Conversation>()
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [{provide: MessengerService, useValue: mockMessengerService}],
      declarations: [ TestHostComponent, MessengerUserComponent ]
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

  it('should show the receiver name', () => {
    const nameDE = fixture.debugElement.query(By.css('p.name'))
    expect(nameDE).toBeTruthy();
    const nameHTML = nameDE.nativeElement;
    expect(nameHTML.textContent).toEqual(component.userComponent.conversation.receiver.name);
  })
});

@Component({
  selector: 'host-component',
  template: '<app-messenger-user [conversation]="testconversation"></app-messenger-user>'
})
class TestHostComponent {
  testconversation = new Conversation(
    new MessageUser(0, "User 0", -1),
    new MessageUser(1, "User 1", -1),
    []
  )

  @ViewChild(MessengerUserComponent)
  public userComponent : MessengerUserComponent
}
