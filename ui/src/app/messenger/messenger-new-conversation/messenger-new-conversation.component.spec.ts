import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessengerNewConversationComponent } from './messenger-new-conversation.component';
import { Observable, of } from 'rxjs';
import { MessageUser, MessengerService } from '@app/messenger/messenger.service';

describe('MessengerNewConversationComponent', () => {
  let component: MessengerNewConversationComponent;
  let fixture: ComponentFixture<MessengerNewConversationComponent>;

  let mockMessengerService = {
    searchUser(name : string) : Observable<MessageUser[]> {
      return of([]);
    },
    newConversation(user : MessageUser) {

    }
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [ {provide: MessengerService, useValue: mockMessengerService}],
      declarations: [ MessengerNewConversationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessengerNewConversationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
