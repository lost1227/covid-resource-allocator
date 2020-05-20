import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessengerComponent } from './messenger.component';
import { Observable, Subject, of } from 'rxjs';
import { MessengerService, Conversation, MessageUser } from '@app/messenger/messenger.service';

describe('MessengerComponent', () => {
  let component: MessengerComponent;
  let fixture: ComponentFixture<MessengerComponent>;

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
      providers: [ {provide: MessengerService, useValue: mockMessengerService}],
      declarations: [ MessengerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessengerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
